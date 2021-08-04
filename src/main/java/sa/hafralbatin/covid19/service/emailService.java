package sa.hafralbatin.covid19.service;

import sa.hafralbatin.covid19.model.email;

import java.util.List;

public interface emailService {

    void create(email email);

    List<email> findAll();

    email findById(int emailId);

    void deleteEmailById(int emailId);


}
