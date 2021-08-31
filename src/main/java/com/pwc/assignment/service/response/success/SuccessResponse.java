package com.pwc.assignment.service.response.success;

import com.pwc.assignment.service.response.Response;
import com.pwc.assignment.service.response.ResponseAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

public abstract class SuccessResponse<T> extends Response {

    public SuccessResponse(HttpStatus status, MultiValueMap headers, String message, T data) {
        super(headers, new ResponseAttributes(status, message, data));
    }
}
