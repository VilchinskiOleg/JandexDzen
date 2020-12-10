package by.tms.service.user.exception;

public class NotFoundUserByIdException extends RuntimeException {
    public NotFoundUserByIdException() {
    }

    public NotFoundUserByIdException(String message) {
        super(message);
    }

    public NotFoundUserByIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundUserByIdException(Throwable cause) {
        super(cause);
    }

    public NotFoundUserByIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return "User with such id does not exist!";
    }
}
