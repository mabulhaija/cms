package com.pwc.assignment.service.response.error.exception;

import com.pwc.assignment.service.response.error.ErrorResponse;


public abstract class ResponseException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public ResponseException(ErrorResponse errorResponse) {
        super(errorResponse.getResponseAttributes().getMessage());
        this.errorResponse = errorResponse;
    }

    public ResponseException(ErrorResponse errorResponse, Throwable e) {
        super(errorResponse.getResponseAttributes().getMessage(), e);
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
