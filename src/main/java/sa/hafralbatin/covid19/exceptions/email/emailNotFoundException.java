package sa.hafralbatin.covid19.exceptions.email;


public class emailNotFoundException extends RuntimeException {

    public emailNotFoundException(String message) {
        super(message);
    }

    public emailNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public emailNotFoundException(Throwable cause) {
        super(cause);
    }

}
