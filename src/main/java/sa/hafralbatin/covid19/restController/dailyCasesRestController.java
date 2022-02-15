package sa.hafralbatin.covid19.restController;
import org.joda.time.Chronology;
import org.joda.time.LocalDate;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.IslamicChronology;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sa.hafralbatin.covid19.model.api_moh_model.dailyCasesGovernorates.dailyCasesFeatures;
import sa.hafralbatin.covid19.model.api_moh_model.dailyCasesGovernorates.dailyCasesGovernorates;
import sa.hafralbatin.covid19.model.dailyCases;
import sa.hafralbatin.covid19.service.Impl.dailyCasesServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class dailyCasesRestController {

    @Autowired
    private dailyCasesServiceImpl casesService;

    int inDatabase = 1;
    int outDatabase = 1;

    @GetMapping("/dailyCases")
    public List<dailyCases> findAll(){
        return casesService.listAll();
    }


    @PostMapping("/addDailyCase")
    public String addDailyCase(@RequestBody dailyCases dailyCases){
        if(dailyCases != null) {
            casesService.create(dailyCases);
            return "Done";
        }
        else
            return "failed to add";
    }

    @PostMapping("/daily-cases-governorates-moh")
    public void getAllDailyCasesFromMoh(){
        String dailyCasesForAllCity = "https://services6.arcgis.com/bKYAIlQgwHslVRaK/arcgis/rest/services/VWPlacesCasesHostedView/FeatureServer/0/query?where=1=1&outFields=*&outSR=4326&f=json";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<dailyCasesGovernorates> responseEntity1 = restTemplate.getForEntity(
                dailyCasesForAllCity
                ,dailyCasesGovernorates.class);

        List<dailyCasesFeatures> dailyCasesList = Arrays.stream(responseEntity1.getBody().getFeatures()).filter(p->p.getAttributes().getGovernorateName_EN().equals("Hafar Al Batin")).collect(Collectors.toList());

        sortDailyCases(dailyCasesList);
        
    }

    public void sortDailyCases(List<dailyCasesFeatures> dailyCasesList){

        dailyCases cases = new dailyCases();
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd-MM-yyyy HH:mm:ss");


        Chronology iso = ISOChronology.getInstanceUTC();
        Chronology hijri = IslamicChronology.getInstanceUTC();

        LocalDate todayIso ;
        LocalDate todayHijri ;

        for (int i = 0; i < dailyCasesList.size(); i++) {
            cases.setID(dailyCasesList.get(i).getAttributes().getId());
            cases.setPlace_Code(dailyCasesList.get(i).getAttributes().getPlace_Code());
            cases.setGOV_CODE(dailyCasesList.get(i).getAttributes().getGOV_CODE());
            cases.setPlaceName_AR(dailyCasesList.get(i).getAttributes().getPlaceName_AR());
            cases.setPlaceName_EN(dailyCasesList.get(i).getAttributes().getPlaceName_EN());
            cases.setGovernorateName_AR(dailyCasesList.get(i).getAttributes().getGovernorateName_AR());
            cases.setGovernorateName_EN(dailyCasesList.get(i).getAttributes().getGovernorateName_EN());
            cases.setRegionName_AR(dailyCasesList.get(i).getAttributes().getRegionName_AR());
            cases.setRegionName_EN(dailyCasesList.get(i).getAttributes().getRegionName_EN());
            cases.setPlaceCategory(dailyCasesList.get(i).getAttributes().getPlace_Category());
            cases.setName_ar(dailyCasesList.get(i).getAttributes().getName_Ar());
            cases.setName_en(dailyCasesList.get(i).getAttributes().getName_Eng());
            cases.setConfirmed(dailyCasesList.get(i).getAttributes().getConfirmed());
            cases.setDeaths(dailyCasesList.get(i).getAttributes().getDeaths());
            cases.setRecovered(dailyCasesList.get(i).getAttributes().getRecovered());
            cases.setTested(dailyCasesList.get(i).getAttributes().getTested());
            cases.setReported_at(new Date(dailyCasesList.get(i).getAttributes().getReportdt()));

            beforeAddToService(cases);

        }

    }
    
   public void beforeAddToService(dailyCases DailyCase){

        List<dailyCases> allDailyCases = findAll();

        boolean reportedAtIsTheSame = allDailyCases.stream().anyMatch(rep -> rep.getReported_at().getTime() == DailyCase.getReported_at().getTime()
                                                                            && rep.getName_en().equals(DailyCase.getName_en()));

        if(reportedAtIsTheSame){
//            System.out.println("["+ (inDatabase++) +"] It's in the database." + "[ "+DailyCase.getName_en()+" - "+DailyCase.getConfirmed()+" - "+DailyCase.getReported_at()+" ]");
        }
        else {
//            System.out.println("["+ (outDatabase++) +"] It's out the database." + "[ "+DailyCase.getName_en()+" - "+DailyCase.getConfirmed()+" - "+DailyCase.getReported_at()+" ]");
            addDailyCase(DailyCase);
        }


   }


}
