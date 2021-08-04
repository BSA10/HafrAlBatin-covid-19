package sa.hafralbatin.covid19.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sa.hafralbatin.covid19.model.dailyCases;
import sa.hafralbatin.covid19.service.Impl.dailyCasesServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class dailyCasesRestController {

    @Autowired
    private dailyCasesServiceImpl casesService;


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


}
