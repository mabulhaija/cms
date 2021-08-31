package com.pwc.assignment.service.response.success;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

public class NoContentResponse extends SuccessResponse {

    public NoContentResponse() {
        super(HttpStatus.NO_CONTENT, null, null, null);
    }

    public NoContentResponse(MultiValueMap headers) {
        super(HttpStatus.NO_CONTENT, headers, null, null);
    }
}
