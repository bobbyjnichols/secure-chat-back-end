package com.ucf.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.google.common.base.CaseFormat;

/**
 * @author Robert Nichols
 * @category Utilities
 */

@Component
public class Response {

    private HttpStatus status;
    private String message;

    public Response(HttpStatus status, String message) {

        this.message = message;
        this.status = status;
    }

    public Response(Object obj) {

        new ResponseEntity<Object>(obj, HttpStatus.OK);
    }

    public Response() {
        super();
    }

    public ResponseEntity<?> respond(HttpStatus status, String message) {
        this.message = message;
        this.status = status;
        return new ResponseEntity<>(this, status);

    }

    public ResponseEntity<?> respond(HttpStatus status) {
        return new ResponseEntity<>(null, status);
    }

    public ResponseEntity<?> respond(Object obj) {
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    public ResponseEntity<?> respond() {
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Response setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {

        JSONObject ret = new JSONObject();

        for (Method m : this.getClass().getMethods()) {
            if (m.getName().startsWith("get") && !m.getName().equals("getClass")) {
                try {
                    ret.put(
                            CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, m.getName().substring(3)),
                            m.invoke(this));
                } catch (JSONException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return ret.toString(2);
    }
}
