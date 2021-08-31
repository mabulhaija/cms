package com.pwc.assignment.service.response;

import org.springframework.http.HttpStatus;

public class ResponseAttributes<T> {

    private HttpStatus status;
    private String message;
    private T data;

    public ResponseAttributes() {
    }

    public ResponseAttributes(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
