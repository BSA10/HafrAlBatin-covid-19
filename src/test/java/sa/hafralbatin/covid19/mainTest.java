package sa.hafralbatin.covid19;

import org.joda.time.Chronology;
import org.joda.time.LocalDate;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.IslamicChronology;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Headers;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import sa.hafralbatin.covid19.model.Governorates;
import sa.hafralbatin.covid19.model.api_moh_model.TotalCasesGovernorates.GovernorateCases;
import sa.hafralbatin.covid19.model.api_moh_model.TotalCasesGovernorates.features;
import sa.hafralbatin.covid19.model.api_moh_model.dailyCasesGovernorates.dailyCasesFeatures;
import sa.hafralbatin.covid19.model.api_moh_model.dailyCasesGovernorates.dailyCasesGovernorates;
import sa.hafralbatin.covid19.model.dailyCases;


import java.net.URISyntaxException;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


public class mainTest {
// الحالات اليومية
//https://services6.arcgis.com/bKYAIlQgwHslVRaK/arcgis/rest/services/VWPlacesCasesHostedView/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json
// https://services6.arcgis.com/bKYAIlQgwHslVRaK/arcgis/rest/services/CasesByGovernoratesViewLayer/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json
    public static void main(String[] args) throws URISyntaxException, ParseException {

        String url = "https://services6.arcgis.com/bKYAIlQgwHslVRaK/arcgis/rest/services/CasesByGovernoratesViewLayer/FeatureServer/0/query?where=1=1&outFields=*&outSR=4326&f=json";
        String url1 = "https://services6.arcgis.com/bKYAIlQgwHslVRaK/arcgis/rest/services/VWPlacesCasesHostedView/FeatureServer/0/query?where=1=1&outFields=*&outSR=4326&f=json";
        RestTemplate restTemplate = new RestTemplate();
//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//
//        // testing
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
//

//        ResponseEntity<CasesByGovernorates> responseEntity = restTemplate.getForEntity(
//                url
//                ,CasesByGovernorates.class);
//        List<features> test = Arrays.stream(responseEntity.getBody().getFeatures()).filter(p->p.getAttributes().getGovernorate_Name_EN().startsWith("Hafar")).collect(Collectors.toList());
//
////        System.out.println(responseEntity.getBody());
////        System.out.println(test.get(0).getAttributes().getTotalActive());
//
//        // This for POST DATA IN RestController
//        Governorates governorates = new Governorates();
//        governorates.setName_ar(test.get(0).getAttributes().getGovernorate_Name_AR());
//        governorates.setName_en(test.get(0).getAttributes().getGovernorate_Name_EN());
//        governorates.setRegion_ar(test.get(0).getAttributes().getRegion_Name_AR());
//        governorates.setRegion_en(test.get(0).getAttributes().getGovernorate_Name_EN());
//        governorates.setTotalConfirmed(test.get(0).getAttributes().getTotalConfirmed());
//        governorates.setTotalRecovered(test.get(0).getAttributes().getTotalRecovered());
//        governorates.setTotalDeaths(test.get(0).getAttributes().getTotalDeaths());
//        governorates.setTotalActive(test.get(0).getAttributes().getTotalActive());
//        governorates.setTotalCalculatedRecords(test.get(0).getAttributes().getTotalCalculatedRecords());
//        governorates.setReported_at(new Date(System.currentTimeMillis()));
//                RestTemplate postData = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Governorates> request = new HttpEntity<Governorates>(governorates,headers);
//        ResponseEntity<String> postAttr = restTemplate.exchange(
//                "http://localhost:8080/api/addTotalStatus"
//                ,HttpMethod.POST
//                ,request
//                ,String.class
//        );







//
       ResponseEntity<dailyCasesGovernorates> responseEntity1 = restTemplate.getForEntity(
                url1
                ,dailyCasesGovernorates.class);

        List<dailyCasesFeatures> test1 = Arrays.stream(responseEntity1.getBody().getFeatures()).filter(p->p.getAttributes().getGovernorateName_EN().equals("Hafar Al Batin")).collect(Collectors.toList());
        System.out.println(test1);

        // place code hafar albatin : C278G50R05

        dailyCases cases = new dailyCases();
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd-MM-yyyy HH:mm:ss");


        RestTemplate postDailyCase = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> responseEntity;
        HttpEntity<dailyCases> request;
        Chronology iso = ISOChronology.getInstanceUTC();
        Chronology hijri = IslamicChronology.getInstanceUTC();

        LocalDate todayIso ;
        LocalDate todayHijri ;
//        int i = test1.size()-1;

        /*for (int i = 0; i < test1.size(); i++) {
            cases.setID(test1.get(i).getAttributes().getId());
            cases.setPlace_Code(test1.get(i).getAttributes().getPlace_Code());
            cases.setGOV_CODE(test1.get(i).getAttributes().getGOV_CODE());
            cases.setPlaceName_AR(test1.get(i).getAttributes().getPlaceName_AR());
            cases.setPlaceName_EN(test1.get(i).getAttributes().getPlaceName_EN());
            cases.setGovernorateName_AR(test1.get(i).getAttributes().getGovernorate_Name_AR());
            cases.setGovernorateName_EN(test1.get(i).getAttributes().getGovernorate_Name_EN());
            cases.setRegionName_AR(test1.get(i).getAttributes().getRegion_Name_AR());
            cases.setRegionName_EN(test1.get(i).getAttributes().getRegion_Name_EN());
            cases.setPlaceCategory(test1.get(i).getAttributes().getPlace_Category());
            cases.setName_ar(test1.get(i).getAttributes().getName_Ar());
            cases.setName_en(test1.get(i).getAttributes().getName_Eng());
            cases.setConfirmed(test1.get(i).getAttributes().getConfirmed());
            cases.setDeaths(test1.get(i).getAttributes().getDeaths());
            cases.setRecovered(test1.get(i).getAttributes().getRecovered());
            cases.setTested(test1.get(i).getAttributes().getTested());
            todayIso = new LocalDate(new Date(test1.get(i).getAttributes().getReportdt()), iso);
            todayHijri = new LocalDate(todayIso.toDateTimeAtStartOfDay() , hijri);
            String x = format.format(new Date(test1.get(i).getAttributes().getReportdt()))+" | "+todayHijri;
            cases.setReported_at(x);

//            request = new HttpEntity<dailyCases>(cases,headers);

            //        ResponseEntity<String> postAttr = restTemplate.exchange(
//                "http://localhost:8080/api/addTotalStatus"
//                ,HttpMethod.POST
//                ,request
//                ,String.class
//        );
//            responseEntity = postDailyCase.exchange(
//                    "http://localhost:8080/api/addDailyCase"
//                    ,HttpMethod.POST
//                    ,request
//                    ,String.class
//            );
        }*/






        /*
        featuresDaily(attributes=attributesDaily(ID=38085, PLC_CODE=C726G50R05, REG_CODE=R05, GOV_CODE=G50R05, PlaceName_AR=حفر الباطن, PlaceName_EN=Hafar al Batin, GovernorateName_AR=حفر الباطن, GovernorateName_EN=Hafar Al Batin, RegionName_AR=المنطقة الشرقية, RegionName_EN=Eastern Region, Place_Category=City, name_ar=حفر الباطن, name_en=Hafar al Batin, Confirmed=12, Deaths=0, Recovered=18, Tested=-6, NewAdded=0, Reportdt=1625130000000))]
         */

        // Note: make RestTemplate for post the data case daily and using RestController of my spring application.
//        RestTemplate postCaseDaily = new RestTemplate();
//        postCaseDaily.setMessageConverters(messageConverters);
//
//        ResponseEntity check = postCaseDaily.exchange(
//                url1,
//                HttpMethod.POST,
//                null,
//                featuresDaily.class
//        );


    }
}


