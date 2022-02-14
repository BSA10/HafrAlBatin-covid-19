package sa.hafralbatin.covid19.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sa.hafralbatin.covid19.model.Governorates;
import sa.hafralbatin.covid19.model.api_moh_model.TotalCasesGovernorates.GovernorateCases;
import sa.hafralbatin.covid19.model.api_moh_model.TotalCasesGovernorates.features;
import sa.hafralbatin.covid19.service.GovernoratesService;

import java.util.*;
import java.util.stream.Collectors;

@EnableAsync
@RestController
@RequestMapping("/api")
public class GovernoratesRestController {

    @Autowired
    private GovernoratesService governoratesService;



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
    public void getCovernorate(){
        String url = "https://services6.arcgis.com/bKYAIlQgwHslVRaK/arcgis/rest/services/CasesByGovernoratesViewLayer/FeatureServer/0/query?where=1=1&outFields=*&outSR=4326&f=json";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<GovernorateCases> responseEntity = restTemplate.getForEntity(
                url
                ,GovernorateCases.class);


        List<features> test = Arrays.stream(responseEntity.getBody().getFeatures()).filter(p->p.getAttributes().getGovernorate_Name_EN().startsWith("Hafar")).collect(Collectors.toList());

        System.out.println(test);

        /***
         * TODO:
         * [x] I have to find a way to handle the objects from JSON file like map the every element and filter.
         * [x] https://stackoverflow.com/questions/67039122/count-each-element-inside-jsonobject-using-java-stream
         * [x] Make a repeated requests from JavaScript to call method to grab the latest data.
         * [x] Make a new Request to the latest corona cases in hafar albatin and filter the one that listed.
         * [] Make a latest corona case by the date and make loop on ModelAttribute for that so that can show as th:each on the day case by the city on hafar albatin governorate.
         */


        Governorates governorates = new Governorates();
        governorates.setName_ar(test.get(0).getAttributes().getGovernorate_Name_AR());
        governorates.setName_en(test.get(0).getAttributes().getGovernorate_Name_EN());
        governorates.setRegion_ar(test.get(0).getAttributes().getRegion_Name_AR());
        governorates.setRegion_en(test.get(0).getAttributes().getGovernorate_Name_EN());
        governorates.setTotalConfirmed(test.get(0).getAttributes().getTotalConfirmed());
        governorates.setTotalRecovered(test.get(0).getAttributes().getTotalRecovered());
        governorates.setTotalDeaths(test.get(0).getAttributes().getTotalDeaths());
        governorates.setTotalActive(test.get(0).getAttributes().getTotalActive());
        governorates.setReported_at(new Date().getTime());
//        RestTemplate postData = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Governorates> request = new HttpEntity<Governorates>(governorates);
//        ResponseEntity<String> postAttr = restTemplate.exchange(
//                "http://localhost:8080/api/addTotalStatus"
//                , HttpMethod.POST
//                ,request
//                ,String.class
//        );
        governoratesService.create(governorates);


//        return governorates;
    }


//    @GetMapping("/last-update")
//    public String addToMySQL(){
//        Governorates governorates = getCovernorate();
//        RestTemplate restTemplate = new RestTemplate();
//
//
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
//
//        return postAttr.getBody();
//    }



}
