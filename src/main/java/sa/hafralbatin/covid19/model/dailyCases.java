package sa.hafralbatin.covid19.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "dailyCases")
public class dailyCases implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String plc_code;
    private String REG_CODE;
    private String GOV_CODE;
    private String PlaceName_AR;
    private String PlaceName_EN;
    private String GovernorateName_AR;
    private String GovernorateName_EN;
    private String RegionName_AR;
    private String RegionName_EN;
    private String PlaceCategory;
    private String name_ar;
    private String name_en;
    private int confirmed;
    private int deaths;
    private int recovered;
    private int tested;
    private int newadded;
    private String reported_at;

}
