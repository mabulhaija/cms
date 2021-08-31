package com.pwc.assignment.service.response.success;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

public class CreatedResponse extends SuccessResponse {

    public CreatedResponse() {
        super(HttpStatus.CREATED, null, null, null);
    }

    public CreatedResponse(MultiValueMap headers) {
        super(HttpStatus.CREATED, headers, null, null);
    }

    public CreatedResponse(String message) {
        super(HttpStatus.CREATED, null, message, null);
    }

    public CreatedResponse(Object data) {
        super(HttpStatus.CREATED, null, null, data);
    }

    public CreatedResponse(MultiValueMap headers, String message) {
        super(HttpStatus.CREATED, headers, message, null);
    }

    public CreatedResponse(MultiValueMap headers, Object data) {
        super(HttpStatus.CREATED, headers, null, data);
    }

    public CreatedResponse(String message, Object data) {
        super(HttpStatus.CREATED, null, message, data);
    }

    public CreatedResponse(MultiValueMap headers, String message, Object data) {
        super(HttpStatus.CREATED, headers, message, data);
    }
}
