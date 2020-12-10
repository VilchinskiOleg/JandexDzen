package by.tms.service.user.exception;

public class NotFoundUserByLoginException extends RuntimeException {
    public NotFoundUserByLoginException() {
    }

    public NotFoundUserByLoginException(String message) {
        super(message);
    }

    public NotFoundUserByLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundUserByLoginException(Throwable cause) {
        super(cause);
    }

    public NotFoundUserByLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return "User with such login does not exist!";
    }
}
