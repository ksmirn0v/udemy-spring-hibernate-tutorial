package com.ksmirnov.demoapp.exception;

public class EmployerNotFoundException extends RuntimeException {

    public EmployerNotFoundException(String message) {
        super(message);
    }

    public EmployerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployerNotFoundException(Throwable cause) {
        super(cause);
    }
}
