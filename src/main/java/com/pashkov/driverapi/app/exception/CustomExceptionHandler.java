package com.pashkov.driverapi.app.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFountException(WebRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        ErrorMessage errorMessage = new ErrorMessage();
        for(Map.Entry<String, String[]> entry : parameterMap.entrySet()){
            errorMessage.setErrorMessage(entry.getKey()+" "+ Arrays.toString(entry.getValue()));
            errorMessage.setHttpStatus(String.valueOf(HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex){
        ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMessage(ex.getMessage());
            errorMessage.setHttpStatus(String.valueOf(HttpStatus.UNAUTHORIZED));

        return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
}
