package sa.hafralbatin.covid19.exceptions.email;

public class emailAddException extends RuntimeException {


    public emailAddException(String message) {
        super(message);
    }

    public emailAddException(String message, Throwable cause) {
        super(message, cause);
    }

    public emailAddException(Throwable cause) {
        super(cause);
    }
}
