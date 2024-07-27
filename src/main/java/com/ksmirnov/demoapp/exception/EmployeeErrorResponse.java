package com.ksmirnov.demoapp.exception;

public class EmployeeErrorResponse {

    private int status;
    private String message;
    private long timestamp;

    public EmployeeErrorResponse() {}

    public EmployeeErrorResponse(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
