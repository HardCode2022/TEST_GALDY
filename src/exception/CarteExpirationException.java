package exception;

public class CarteExpirationException extends Exception{

    public CarteExpirationException() {
    }

    public CarteExpirationException(String message) {
        super(message);
    }

    public CarteExpirationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarteExpirationException(Throwable cause) {
        super(cause);
    }

    public CarteExpirationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
