package exception;

public class FailedAuthenticationException extends Exception {
    public FailedAuthenticationException(Exception ex) {
        super(ex);
    }

    public FailedAuthenticationException(String message) {
        super(message);
    }

    public FailedAuthenticationException(String message, Exception ex) {
        super(message, ex);
    }
}
