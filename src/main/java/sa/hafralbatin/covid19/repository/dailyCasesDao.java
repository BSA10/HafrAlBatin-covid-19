package sa.hafralbatin.covid19.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sa.hafralbatin.covid19.model.dailyCases;

import java.util.List;

@Repository
public interface dailyCasesDao extends JpaRepository<dailyCases,Integer> {

    @Query("select max(d) from dailyCases d where d.name_en=:name order by d.reported_at desc")
     dailyCases getLastOneName(String name);

    @Query("select max(d) from dailyCases d group by d order by d.reported_at desc")
    List<dailyCases> getLastOneByDate();

}
