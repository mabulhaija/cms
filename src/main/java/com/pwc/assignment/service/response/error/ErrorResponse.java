package com.pwc.assignment.service.response.error;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.pwc.assignment.service.response.Response;
import com.pwc.assignment.service.response.ResponseAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ErrorResponse extends Response {

    public ErrorResponse(HttpStatus status, MultiValueMap headers, String message) {
        super(headers, new ResponseAttributes(status, message, null));
    }
}
