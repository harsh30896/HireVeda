package com.hireVeda.controller.advice;

import com.hireVeda.excption.InvalidCredentialException;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice

public class WeatherControllerAdvice {

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity <Map<String,Object>> handleInvalidCridentialExceptions(InvalidCredentialException invalidCredentialException){
        Map<String,Object> errorMap = new HashMap<>();
        errorMap.put("message",invalidCredentialException.getMessage());
        errorMap.put("status", "false");
        return new ResponseEntity<>(errorMap, HttpStatus.UNAUTHORIZED);
    }
}
