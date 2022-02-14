package sa.hafralbatin.covid19.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "Governorates")
public class Governorates implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_en")
    private String name_en;


    @Column(name = "name_ar")
    private String name_ar;

    @Column(name = "region_en")
    private String region_en;
    @Column(name = "region_ar")
    private String region_ar;
    @Column(name = "TotalConfirmed")
    private int TotalConfirmed;
    @Column(name = "TotalDeaths")
    private int TotalDeaths;
    @Column(name = "TotalRecovered")
    private int TotalRecovered;
    @Column(name = "TotalActive")
    private int TotalActive;
    @Column(name = "TotalCalculatedRecords")
    private int TotalCalculatedRecords;

    private long reported_at;



}
