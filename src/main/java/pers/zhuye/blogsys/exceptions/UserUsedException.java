package pers.zhuye.blogsys.exceptions;

public class UserUsedException extends RuntimeException {

    public UserUsedException() {
        super();
    }

    public UserUsedException(String message) {
        super(message);
    }

    public UserUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserUsedException(Throwable cause) {
        super(cause);
    }

    protected UserUsedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
