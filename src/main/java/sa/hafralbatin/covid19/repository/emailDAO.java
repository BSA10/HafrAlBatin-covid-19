package sa.hafralbatin.covid19.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.hafralbatin.covid19.model.email;
@Repository
public interface emailDAO extends JpaRepository<email,Integer> {
}
