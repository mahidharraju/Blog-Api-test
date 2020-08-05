package com.myblog.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerResponse {

    public static <T> ResponseEntity<T> getOkResposeEntity(T response)
    {
        return new ResponseEntity<T>(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> getCreatedResposeEntity(T response)
    {
        return new ResponseEntity<T>(response, HttpStatus.CREATED);
    }


    public static <T> ResponseEntity<T> getServerErrorResponseEntity() {
        return new ResponseEntity("InValid request Format", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<Object> getNotFoundResponseEntity() {
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}
