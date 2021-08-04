package sa.hafralbatin.covid19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sa.hafralbatin.covid19.service.Impl.dailyCasesServiceImpl;

@Controller
public class dailyCasesController {

    @Autowired
    private dailyCasesServiceImpl casesService;

    @GetMapping("/daily-cases")
    public String dailyCasesIndex(Model model){
        model.addAttribute("cases",casesService.listAll());
        return "daily-cases";
    }

}
