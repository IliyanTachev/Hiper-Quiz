package exception;

public class NoAuthorFoundException extends Exception{
    public NoAuthorFoundException() {
    }

    public NoAuthorFoundException(String message) {
        super(message);
    }

    public NoAuthorFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAuthorFoundException(Throwable cause) {
        super(cause);
    }
}
