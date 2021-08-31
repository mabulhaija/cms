package com.pwc.assignment.service.response.error.exception.client;


import com.pwc.assignment.service.response.error.ErrorResponse;
import com.pwc.assignment.service.response.error.exception.ResponseException;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends ResponseException {

    public ForbiddenException(String message) {
        super(new ErrorResponse(HttpStatus.FORBIDDEN, null, message));
    }

}
