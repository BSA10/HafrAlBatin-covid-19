package sa.hafralbatin.covid19.restController;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import sa.hafralbatin.covid19.exceptions.email.emailAddException;
import sa.hafralbatin.covid19.exceptions.email.emailErrorResponse;
import sa.hafralbatin.covid19.exceptions.email.emailNotFoundException;
import sa.hafralbatin.covid19.model.Governorates;
import sa.hafralbatin.covid19.model.email;
import sa.hafralbatin.covid19.service.Impl.GovernoratesServiceImpl;
import sa.hafralbatin.covid19.service.Impl.emailServiceImpl;



@RestController
@RequestMapping("/api/email")
public class emailsRESTController {


    @Autowired
    private emailServiceImpl emailService;

    @Autowired
    private GovernoratesServiceImpl governoratesService;



    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/{emailId}")
    public email getEmail(@PathVariable int emailId){
        if(emailId <= 0 || emailId >= emailService.findAll().size())
            throw new emailNotFoundException("Email is not found - "+emailId);

        return emailService.findById(emailId);

    }


    @PostMapping("/addEmail")
    public void addEmail(@ModelAttribute email email) {
        if(email == null || email.getEmails().isBlank()  || email.getEmails().isEmpty() )
            throw new emailAddException("Error while add Email");
        if(!email.getEmails().matches("^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$"))
            emailService.create(email);
    }

//    @PostMapping("/sendToEmails") // handle all the emails and send them the data OneByOne
//    public void sendToEmails(){
//
//        Governorates governorates = governoratesService.getAll().get(governoratesService.getAll().size()-1);
//        for (int i = 1; i <= emailService.findAll().size(); i++) {
//            email tempEmail = emailService.findById(i);
//            String message = "Hafar Albatin Status \n"
//                            +"\nTotal Confirmed: " +governorates.getTotalConfirmed()
//                            +"\nTotal Deaths: " +governorates.getTotalDeaths()
//                            +"\nTotal Active: " +governorates.getTotalActive()
//                            +"\nTotal Recovered: " +governorates.getTotalRecovered();
//            Email email = EmailBuilder.startingBlank()
//                    .from("BASSAM", "so-ld@hotmail.com")
//                    .to(tempEmail.getName(), tempEmail.getEmails())
//                    .withSubject("Hafar Albatin Covid19 Status")
//                    .withPlainText(message)
//                    .buildEmail();
//            mailerBuild.sendMail(email);
//        }
//
//    }


    // testing the speed between the two //
    @PostMapping("/send1")
    public void send1(){ // 7.28
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("so-ld@hotmail.com");
        message.setTo("s0-ld@hotmail.com");
        message.setSubject("Testing Mail API");
        message.setText("Testing number 1 send");
        javaMailSender.send(message);
    }


    @ExceptionHandler
    public ResponseEntity<emailErrorResponse> handleException(emailNotFoundException exception){
        emailErrorResponse errorResponse = new emailErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse , HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<emailErrorResponse> handleAddException(emailAddException exception){
        emailErrorResponse errorResponse = new emailErrorResponse();
        errorResponse.setStatus(HttpStatus.NO_CONTENT.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse , HttpStatus.NOT_FOUND);
    }

}
