package sa.hafralbatin.covid19.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {



    @GetMapping("/test")
    public String getTest(){
        return "test";
    }


}
