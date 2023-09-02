package com.Aforv.aop.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.http.WebSocket;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler({EntryNotFoundException.class})
    public ResponseEntity<Map<String,String>> entryNotFoundExceptionHandler(EntryNotFoundException ex){
        Map<String,String> errorResponse= new HashMap<>();
        errorResponse.put("TimeStamp", LocalTime.now().toString());
        errorResponse.put("massage", ex.getMessage());
        return  new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidUserException.class})
    public ResponseEntity<Map<String,String>> InvalidUserException(InvalidUserException ex){
        Map<String,String> errorResponse= new HashMap<>();
        errorResponse.put("TimeStamp", LocalTime.now().toString());
        errorResponse.put("massage", ex.getMessage());
        return  new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
