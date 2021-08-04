package sa.hafralbatin.covid19.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sa.hafralbatin.covid19.model.Governorates;
import sa.hafralbatin.covid19.service.Impl.GovernoratesServiceImpl;
import java.util.*;

@EnableAsync
@RestController
@RequestMapping("/api")
public class GovernoratesRestController {

    @Autowired
    private GovernoratesServiceImpl governoratesService;

    @GetMapping("/test")
    public String getTest(){
        return "test-from-RestController-GovernoratesController";
    }


    @GetMapping("/status")
    public List<Governorates> getStatus(){
        return governoratesService.getAll();
    }


    @PostMapping("/addTotalStatus")
    public String addGovernorates(@RequestBody Governorates governorates){
        governoratesService.create(governorates);
        return "Done.";
    }


    @GetMapping("/last-status-moh")
    public Governorates getCovernorate(){
        String url = "https://services6.arcgis.com/bKYAIlQgwHslVRaK/arcgis/rest/services/CasesByGovernoratesViewLayer/FeatureServer/0/query?where=1=1&outFields=*&outSR=4326&f=json";
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
//        ResponseEntity<CasesByGovernorates> responseEntity = restTemplate.getForEntity(
//                url
//                ,CasesByGovernorates.class);
//        List<features> test = Arrays.stream(responseEntity.getBody().getFeatures()).filter(p->p.getAttributes().getGovernorate_Name_EN().startsWith("Hafar")).collect(Collectors.toList());
//
        Governorates governorates = new Governorates();
//        governorates.setName_ar(test.get(0).getAttributes().getGovernorate_Name_AR());
//        governorates.setName_en(test.get(0).getAttributes().getGovernorate_Name_EN());
//        governorates.setRegion_ar(test.get(0).getAttributes().getRegion_Name_AR());
//        governorates.setRegion_en(test.get(0).getAttributes().getGovernorate_Name_EN());
//        governorates.setTotalConfirmed(test.get(0).getAttributes().getTotalConfirmed());
//        governorates.setTotalRecovered(test.get(0).getAttributes().getTotalRecovered());
//        governorates.setTotalDeaths(test.get(0).getAttributes().getTotalDeaths());
//        governorates.setTotalActive(test.get(0).getAttributes().getTotalActive());
//        governorates.setTotalCalculatedRecords(test.get(0).getAttributes().getTotalCalculatedRecords());
//        governorates.setReported_at(new Date().getTime());
//        RestTemplate postData = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Governorates> request = new HttpEntity<Governorates>(governorates,headers);
//        ResponseEntity<String> postAttr = restTemplate.exchange(
//                "http://localhost:8080/api/addTotalStatus"
//                , HttpMethod.POST
//                ,request
//                ,String.class
//        );

        return governorates;
    }


    @GetMapping("/last-update")
    public String addToMySQL(){
        Governorates governorates = getCovernorate();
        RestTemplate restTemplate = new RestTemplate();


        RestTemplate postData = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Governorates> request = new HttpEntity<Governorates>(governorates,headers);
        ResponseEntity<String> postAttr = restTemplate.exchange(
                "http://localhost:8080/api/addTotalStatus"
                , HttpMethod.POST
                ,request
                ,String.class
        );

        return postAttr.getBody();
    }



}
