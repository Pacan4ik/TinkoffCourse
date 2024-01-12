package edu.project1;

class BadWordsSourceException extends Exception {
    @SuppressWarnings("AvoidNoArgumentSuperConstructorCall") BadWordsSourceException() {
        super();
    }

    BadWordsSourceException(String message) {
        super(message);
    }

    BadWordsSourceException(Throwable cause) {
        super(cause);
    }

    BadWordsSourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
