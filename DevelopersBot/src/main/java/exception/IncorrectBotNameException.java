package exception;

public class IncorrectBotNameException extends Exception {
    public IncorrectBotNameException(Exception ex) {
        super(ex);
    }

    public IncorrectBotNameException(String message) {
        super(message);
    }

    public IncorrectBotNameException(String message, Exception ex) {
        super(message, ex);
    }
}
