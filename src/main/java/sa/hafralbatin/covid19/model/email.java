package sa.hafralbatin.covid19.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;




@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "email")
public class email implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "email")
    private String emails;

}
