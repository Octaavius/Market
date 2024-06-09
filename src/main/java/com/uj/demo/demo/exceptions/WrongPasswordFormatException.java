package com.uj.demo.demo.exceptions;

public class WrongPasswordFormatException extends RuntimeException{
    public WrongPasswordFormatException() {
        super();
    }

    public WrongPasswordFormatException(String message) {
        super(message);
    }

    public WrongPasswordFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongPasswordFormatException(Throwable cause) {
        super(cause);
    }
}
