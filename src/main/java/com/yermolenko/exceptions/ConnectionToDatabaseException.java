package com.yermolenko.exceptions;

public class ConnectionToDatabaseException extends RuntimeException {

    public ConnectionToDatabaseException() {
    }

    public ConnectionToDatabaseException(String message) {
        super(message);
    }

    public ConnectionToDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionToDatabaseException(Throwable cause) {
        super(cause);
    }
}
