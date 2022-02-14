package sa.hafralbatin.covid19.model.api_moh_model.TotalCasesGovernorates;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class attributes {

    @JsonProperty("Governorate_Name_AR")
    private String Governorate_Name_AR;

    @JsonProperty("Governorate_Name_EN")
    private String Governorate_Name_EN;

    @JsonProperty("Region_Name_AR")
    private String Region_Name_AR;

    @JsonProperty("Region_Name_EN")
    private String Region_Name_EN;

    @JsonProperty("TotalConfirmed")
    private int TotalConfirmed;

    @JsonProperty("TotalDeaths")
    private int TotalDeaths;

    @JsonProperty("TotalRecovered")
    private int TotalRecovered;

    @JsonProperty("TotalActive")
    private int TotalActive;


}
