package sa.hafralbatin.covid19.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.hafralbatin.covid19.model.email;

public interface emailDAO extends JpaRepository<email,Integer> {
}
