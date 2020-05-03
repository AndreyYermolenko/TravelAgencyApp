package com.yermolenko.exceptions;

/**
 * Class ConnectionToDatabaseException generates errors when there is no connection to the database.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
public class ConnectionToDatabaseException extends RuntimeException {

    /**
     * Constructor ConnectionToDatabaseException creates a new ConnectionToDatabaseException instance.
     */
    public ConnectionToDatabaseException() {
    }

    /**
     * Constructor ConnectionToDatabaseException creates a new ConnectionToDatabaseException instance.
     *
     * @param message of type String
     */
    public ConnectionToDatabaseException(String message) {
        super(message);
    }

    /**
     * Constructor ConnectionToDatabaseException creates a new ConnectionToDatabaseException instance.
     *
     * @param message of type String
     * @param cause of type Throwable
     */
    public ConnectionToDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor ConnectionToDatabaseException creates a new ConnectionToDatabaseException instance.
     *
     * @param cause of type Throwable
     */
    public ConnectionToDatabaseException(Throwable cause) {
        super(cause);
    }
}
