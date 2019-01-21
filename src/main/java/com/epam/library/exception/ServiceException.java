package com.epam.library.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = -3616052389063923941L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
