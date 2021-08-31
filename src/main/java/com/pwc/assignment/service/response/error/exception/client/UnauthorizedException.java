package com.pwc.assignment.service.response.error.exception.client;

import com.pwc.assignment.service.response.error.ErrorResponse;
import com.pwc.assignment.service.response.error.exception.ResponseException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ResponseException {

    public UnauthorizedException(String message) {
        super(new ErrorResponse(HttpStatus.UNAUTHORIZED, null, message));
    }
}
