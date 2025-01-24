package app.library.exceptions;

public class AuthorRepositoryException extends RepositoryException {

    public AuthorRepositoryException(String message) {
        super(message);
    }

    public AuthorRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
