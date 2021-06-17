package hiperquiz.exception;

public class InvalidEntityDataException extends RuntimeException{
    public InvalidEntityDataException() {
    }

    public InvalidEntityDataException(String message) {
        super(message);
    }

    public InvalidEntityDataException(Throwable cause) {
        super(cause);
    }
}
