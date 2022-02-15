package sa.hafralbatin.covid19.restController;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sa.hafralbatin.covid19.exceptions.email.emailAddException;
import sa.hafralbatin.covid19.exceptions.email.emailErrorResponse;
import sa.hafralbatin.covid19.exceptions.email.emailNotFoundException;
import sa.hafralbatin.covid19.model.email;
import sa.hafralbatin.covid19.service.Impl.GovernoratesServiceImpl;
import sa.hafralbatin.covid19.service.Impl.emailServiceImpl;

import javax.validation.constraints.Size;



@RestController
@RequestMapping("/api/email")
@Validated
public class emailsRESTController {


    @Autowired
    private emailServiceImpl emailService;

    @Autowired
    private GovernoratesServiceImpl governoratesService;



    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/{emailId}")
    public ResponseEntity<email> getEmail(@Size(min = 0 , max = 500 , message = "inside @Size") @PathVariable int emailId){
//        if(emailId <= 0 || emailId >= emailService.findAll().size())
//            throw new emailNotFoundException("Email is not found - "+ emailId);

        return ResponseEntity.ok(emailService.findById(emailId));

    }


    @PostMapping("/addEmail")
    public void addEmail(@ModelAttribute email email) {
        if(email == null || email.getEmails().isBlank()  || email.getEmails().isEmpty() )
            throw new emailAddException("Error while add Email");
        if(!email.getEmails().matches("^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$"))
            emailService.create(email);
    }


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


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<emailErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex) {
        emailErrorResponse errorResponse = new emailErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
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
