package sa.hafralbatin.covid19.exceptions.email;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class emailErrorResponse {

    private int status;
    private String message;
    private long timeStamp;

}
