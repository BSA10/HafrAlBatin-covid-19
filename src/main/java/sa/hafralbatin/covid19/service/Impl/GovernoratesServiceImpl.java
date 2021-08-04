package sa.hafralbatin.covid19.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import sa.hafralbatin.covid19.doa.GovernoratesDoa;
import sa.hafralbatin.covid19.model.Governorates;
import sa.hafralbatin.covid19.service.GovernoratesService;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"status"})
public class GovernoratesServiceImpl implements GovernoratesService {

    @Autowired
    private GovernoratesDoa governoratesDoa;


    @Override
    public void create(Governorates governorates) {
        governoratesDoa.save(governorates);
    }

    @Override
    @Cacheable
    public List<Governorates> getAll() {
        return governoratesDoa.findAll();
    }

    @Override
    @CachePut
    public void delete(int id) {

    }
}
