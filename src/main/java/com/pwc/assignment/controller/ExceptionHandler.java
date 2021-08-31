package com.pwc.assignment.controller;

import com.pwc.assignment.service.response.Response;
import com.pwc.assignment.service.response.error.exception.ResponseException;
import com.pwc.assignment.service.response.error.exception.client.BadRequestException;
import com.pwc.assignment.service.response.error.exception.server.ServerErrorException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    protected Response handleError(Exception e) {

        ResponseException exception;

        if (e instanceof ResponseException) {
            exception = (ResponseException) e;
            if (exception instanceof ServerErrorException) {
                logger.error(exception.getMessage(), exception);
            }
        } else if (e instanceof ConstraintViolationException) {
            exception = new BadRequestException(e.getMessage());
        } else {
            logger.error(e.getMessage(), e);
            exception = new ServerErrorException(e.getMessage());
        }

        return exception.getErrorResponse();
    }
}
