package sa.hafralbatin.covid19.model.api_moh_model.dailyCasesGovernorates;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class dailyCasesGovernorates {


    @JsonProperty("features")
    private dailyCasesFeatures[] features;

}
