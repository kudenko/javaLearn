package app.library.exception;

public class JournalRepositoryException extends RepositoryException {

    public JournalRepositoryException(String message) {
        super(message);
    }

    public JournalRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
