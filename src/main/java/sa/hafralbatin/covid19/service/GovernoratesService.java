package sa.hafralbatin.covid19.service;

import sa.hafralbatin.covid19.model.Governorates;

import java.util.List;

public interface GovernoratesService {

    void create(Governorates governorates);

    List<Governorates> getAll();

    void delete(int id);


}
