package com.pwc.assignment.service.response.error.exception.client;


import com.pwc.assignment.service.response.error.ErrorResponse;
import com.pwc.assignment.service.response.error.exception.ResponseException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends ResponseException {

    public BadRequestException(String message) {
        super(new ErrorResponse(HttpStatus.BAD_REQUEST, null, message));
    }
}
