package sa.hafralbatin.covid19.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SubdomainController(value = {"www","test"})
public class testController {



    @RequestMapping(value = { "", "/" })
    public ResponseEntity<?> index() {
        return ResponseEntity.ok("Hello from www.${your-domain} or test.${your-domain}");
    }


}
