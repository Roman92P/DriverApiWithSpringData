package com.pashkov.driverapi.app.exception;

public class ErrorMessage {

    private String httpStatus;
    private String errorMessage;



    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    @Override
    public String toString() {
        return "ErrorMessage{" +
                "httpStatus='" + httpStatus + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}

