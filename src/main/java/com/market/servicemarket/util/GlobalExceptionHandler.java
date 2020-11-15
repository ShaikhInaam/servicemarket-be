package com.market.servicemarket.util;

import com.market.servicemarket.response.BaseResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(Constants.INVALID_FIELD_RESPONSE_CODE);
        response.setResponseMessage(Constants.INVALID_FIELD_RESPONSE_MESSAGE);
        ex.printStackTrace();
        return ResponseEntity.ok(response);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(Constants.INVALID_FIELD_RESPONSE_CODE);
        response.setResponseMessage(Constants.INVALID_FIELD_RESPONSE_MESSAGE);
        ex.printStackTrace();
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeExceptions(RuntimeException exception, WebRequest webRequest) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(Constants.FAILUARE_RESPNSE_CODE);
        response.setResponseMessage(Constants.FAILUARE_RESPONSE_MESSAGE);
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.OK);
        exception.printStackTrace();
        return entity;
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception exception, WebRequest webRequest) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(Constants.FAILUARE_RESPNSE_CODE);
        response.setResponseMessage(Constants.FAILUARE_RESPONSE_MESSAGE);
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.OK);
        exception.printStackTrace();
        return entity;
    }
}
