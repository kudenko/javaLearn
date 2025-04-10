package app.library.exception;

public class BookRepositoryException extends RepositoryException {

    public BookRepositoryException(String message) {
        super(message);
    }

    public BookRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
