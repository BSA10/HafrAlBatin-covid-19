package sa.hafralbatin.covid19.model.api_moh_model.dailyCasesGovernorates;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class dailyCasesAttributes {


    @JsonProperty("ObjectId")
    private int id;

    @JsonProperty("PlaceName_AR")
    private String PlaceName_AR;
    @JsonProperty("PlaceName_EN")
    private String PlaceName_EN;

    @JsonProperty("GovernorateName_AR")
    private String GovernorateName_AR;

    @JsonProperty("GovernorateName_EN")
    private String GovernorateName_EN;

    @JsonProperty("RegionName_AR")
    private String RegionName_AR;

    @JsonProperty("RegionName_EN")
    private String RegionName_EN;

    @JsonProperty("Place_Code")
    private String Place_Code;
    @JsonProperty("REG_CODE")
    private String REG_CODE;
    @JsonProperty("GOV_CODE")
    private String GOV_CODE;
    @JsonProperty("Place_Category")
    private String Place_Category;

    @JsonProperty("Name_Ar")
    private String Name_Ar;
    @JsonProperty("Name_Eng")
    private String Name_Eng;





    @JsonProperty("Confirmed")
    private int Confirmed;
    @JsonProperty("Deaths")
    private int Deaths;
    @JsonProperty("Recovered")
    private int Recovered;
    @JsonProperty("Tested")
    private int Tested;
    @JsonProperty("NewAdded")
    private int NewAdded;

    @JsonProperty("Reportdt")
    private long Reportdt;


}
