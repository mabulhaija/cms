package com.pwc.assignment.service.response.error.exception.client;


import com.pwc.assignment.service.response.error.ErrorResponse;
import com.pwc.assignment.service.response.error.exception.ResponseException;
import org.springframework.http.HttpStatus;



public class NotFoundException extends ResponseException {

    public NotFoundException(String message) {
        super(new ErrorResponse(HttpStatus.NOT_FOUND, null, message));
    }
}
