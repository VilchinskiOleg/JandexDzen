package by.tms.service.post.exception;

public class DuplicatePostException extends RuntimeException {
    public DuplicatePostException() {
    }

    public DuplicatePostException(String message) {
        super(message);
    }

    public DuplicatePostException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatePostException(Throwable cause) {
        super(cause);
    }

    public DuplicatePostException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return "Post with such title already exist!";
    }
}
