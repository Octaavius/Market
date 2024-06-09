package com.uj.demo.demo.exceptions;

public class WrongLoginOrPasswordException extends RuntimeException{
    public WrongLoginOrPasswordException() {
        super();
    }

    public WrongLoginOrPasswordException(String message) {
        super(message);
    }

    public WrongLoginOrPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongLoginOrPasswordException(Throwable cause) {
        super(cause);
    }
}
