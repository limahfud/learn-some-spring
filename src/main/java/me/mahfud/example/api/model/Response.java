package me.mahfud.example.api.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Response {

    private HttpStatus status;
    private List<String> errors;
    private Object data;

    public Response(HttpStatus status, List<String> errors, Object data) {
        this.status = status;
        this.errors = errors;
        this.data = data;
    }

    public Response(HttpStatus status, String errors, Object data) {
        this.status = status;
        this.errors = Collections.singletonList(errors);
        this.data = data;
    }

    public Response(HttpStatus status, Object data) {
        this.status = status;
        this.data = data;
        this.errors = new ArrayList<>();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public Object getData() {
        if (data instanceof List) {
            HashMap<String, List> map = new HashMap<>();
            map.put("list", (List) data);
            return map;
        }
        return data;
    }
}
