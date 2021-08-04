package sa.hafralbatin.covid19.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import sa.hafralbatin.covid19.doa.dailyCasesDao;
import sa.hafralbatin.covid19.model.dailyCases;
import sa.hafralbatin.covid19.service.dailyCasesService;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames={"dailyCases"})
public class dailyCasesServiceImpl implements dailyCasesService {

    @Autowired
    private dailyCasesDao casesDao;

    @Override
    @CachePut
    public void create(dailyCases dailyCases) {
        casesDao.save(dailyCases);
    }

    @Override
    @CachePut
    public void delete(dailyCases dailyCases) {
        casesDao.delete(dailyCases);
    }

    @Override
    @CachePut
    public void deleteById(int dailyCasesId) {
        casesDao.deleteById(dailyCasesId);
    }

    @Override
    @Cacheable
    public List<dailyCases> listAll() {
        return casesDao.findAll();
    }

    @Override
    public dailyCases findById(int dailyCasesId) {
        Optional<dailyCases> temp = casesDao.findById(dailyCasesId);
        return temp.get();
    }
}
