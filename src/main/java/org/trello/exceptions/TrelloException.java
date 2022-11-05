package org.trello.exceptions;

public class TrelloException extends RuntimeException {

    public TrelloException() {
    }

    public TrelloException(String message) {
        super(message);
    }

    public TrelloException(String message, Throwable cause) {
        super(message, cause);
    }
}
