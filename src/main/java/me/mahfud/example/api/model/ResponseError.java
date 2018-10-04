package me.mahfud.example.api.model;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ResponseError extends Response{

    public ResponseError(HttpStatus status, List<String> errors) {
        super(status, errors, null);
    }

    public ResponseError(HttpStatus status, String errors) {
        super(status, errors, null);
    }
}
