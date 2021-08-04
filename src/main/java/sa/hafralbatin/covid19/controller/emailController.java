package sa.hafralbatin.covid19.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sa.hafralbatin.covid19.model.email;
import sa.hafralbatin.covid19.service.Impl.emailServiceImpl;


@Controller
public class emailController {

    @Autowired
    private emailServiceImpl emailService;

    @GetMapping("/email")
    public String getEmail(Model model){
        email email = new email();
        model.addAttribute("addEmail",email);
        return "email";
    }






}
