package sa.hafralbatin.covid19.service;

import sa.hafralbatin.covid19.model.dailyCases;

import java.util.List;

public interface dailyCasesService {

    void create(dailyCases dailyCases);

    void delete(dailyCases dailyCases);

    void deleteById(int dailyCasesId);

    List<dailyCases> listAll();

    dailyCases findById(int dailyCasesId);



}
