package com.pwc.assignment.service.response.error.exception.server;


import com.pwc.assignment.service.response.error.ErrorResponse;
import com.pwc.assignment.service.response.error.exception.ResponseException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ServerErrorException extends ResponseException {

    public ServerErrorException(String message) {
        super(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, message));
    }

    public ServerErrorException(String message, Throwable e) {
        super(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, message), e);
    }
}
