package com.pwc.assignment.service.response;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public abstract class Response extends ResponseEntity {

    private final ResponseAttributes responseAttributes;

    public Response(MultiValueMap headers, ResponseAttributes responseAttributes) {
        super(responseAttributes, headers, responseAttributes.getStatus());
        this.responseAttributes = responseAttributes;
    }

    public ResponseAttributes getResponseAttributes() {
        return responseAttributes;
    }
}
