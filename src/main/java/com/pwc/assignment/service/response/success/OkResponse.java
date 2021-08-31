package com.pwc.assignment.service.response.success;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

public class OkResponse extends SuccessResponse {

    public OkResponse() {
        super(HttpStatus.OK, null, null, null);
    }

    public OkResponse(MultiValueMap headers) {
        super(HttpStatus.OK, headers, null, null);
    }

    public OkResponse(String message) {
        super(HttpStatus.OK, null, message, null);
    }

    public OkResponse(Object data) {
        super(HttpStatus.OK, null, null, data);
    }

    public OkResponse(MultiValueMap headers, String message) {
        super(HttpStatus.OK, headers, message, null);
    }

    public OkResponse(MultiValueMap headers, Object data) {
        super(HttpStatus.OK, headers, null, data);
    }

    public OkResponse(String message, Object data) {
        super(HttpStatus.OK, null, message, data);
    }

    public OkResponse(MultiValueMap headers, String message, Object data) {
        super(HttpStatus.OK, headers, message, data);
    }
}
