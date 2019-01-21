package com.epam.library.exception;

public class RepositoryException extends Exception {
    private static final long serialVersionUID = 8339343639617072062L;

    public RepositoryException() {
    }

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }
}
