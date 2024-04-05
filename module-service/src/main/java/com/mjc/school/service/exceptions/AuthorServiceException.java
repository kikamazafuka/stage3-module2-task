package com.mjc.school.service.exceptions;

public class AuthorServiceException extends RuntimeException {
    private final String errorCode;

    public AuthorServiceException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
