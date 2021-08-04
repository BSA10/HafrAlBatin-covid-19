package sa.hafralbatin.covid19.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.hafralbatin.covid19.doa.emailDAO;
import sa.hafralbatin.covid19.model.email;
import sa.hafralbatin.covid19.service.emailService;

import java.util.List;
import java.util.Optional;

@Service
public class emailServiceImpl implements emailService {

    @Autowired
    private emailDAO emailDAO;

    @Override
    public void create(email email) {
        emailDAO.save(email);
    }

    @Override
    public List<email> findAll() {
        return emailDAO.findAll();
    }

    @Override
    public email findById(int emailId) {
        Optional<email> email = emailDAO.findById(emailId);
        return email.get();
    }

    @Override
    public void deleteEmailById(int emailId) {
        emailDAO.deleteById(emailId);
    }
}
