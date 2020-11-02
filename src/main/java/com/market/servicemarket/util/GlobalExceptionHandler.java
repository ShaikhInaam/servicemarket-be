package com.market.servicemarket.util;

import com.market.servicemarket.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeExceptions(RuntimeException exception, WebRequest webRequest) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(Constants.FAILUARE_RESPNSE_CODE);
        response.setResponseMessage(Constants.FAILUARE_RESPONSE_MESSAGE);
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.OK);
        return entity;
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception exception, WebRequest webRequest) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(Constants.FAILUARE_RESPNSE_CODE);
        response.setResponseMessage(Constants.FAILUARE_RESPONSE_MESSAGE);
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.OK);
        return entity;
    }
}
