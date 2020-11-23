package com.market.servicemarket.util;

import com.market.servicemarket.response.BaseResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.mail.MessagingException;
import java.io.PrintWriter;
import java.io.StringWriter;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    private JavaMailUtil mailUtil;

    @Autowired
    ConfigurationUtil configurationUtil;

    String emails = "";
    StringWriter errors = new StringWriter();

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        emails = configurationUtil.getMessage(Constants.ADMIN_EMAILS);
        BaseResponse response = new BaseResponse();
        response.setResponseCode(Constants.INVALID_FIELD_RESPONSE_CODE);
        response.setResponseMessage(configurationUtil.getMessage(Constants.INVALID_FIELD_RESPONSE_CODE));
        ex.printStackTrace(new PrintWriter(errors));
        errors.toString();
        try {
            mailUtil.sendMailToAdmin(emails,errors);
        } catch (MessagingException e) {
            RetryEmailSending(e,emails,errors);
        }
        return ResponseEntity.ok(response);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        emails = configurationUtil.getMessage(Constants.ADMIN_EMAILS);
        BaseResponse response = new BaseResponse();
        response.setResponseCode(Constants.INVALID_FIELD_RESPONSE_CODE);
        response.setResponseMessage(configurationUtil.getMessage(Constants.INVALID_FIELD_RESPONSE_CODE));
        ex.printStackTrace(new PrintWriter(errors));
        errors.toString();
        try {
            mailUtil.sendMailToAdmin(emails,errors);
        } catch (MessagingException e) {
            RetryEmailSending(e,emails,errors);
        }

        return ResponseEntity.ok(response);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeExceptions(RuntimeException exception, WebRequest webRequest) {
        emails = configurationUtil.getMessage(Constants.ADMIN_EMAILS);
        BaseResponse response = new BaseResponse();
        response.setResponseCode(Constants.FAILUARE_RESPNSE_CODE);
        response.setResponseMessage(configurationUtil.getMessage(Constants.FAILUARE_RESPNSE_CODE));
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.OK);

        exception.printStackTrace(new PrintWriter(errors));
        errors.toString();
        try {
            mailUtil.sendMailToAdmin(emails,errors);
        } catch (MessagingException e) {
            RetryEmailSending(e,emails,errors);
        }
        return entity;
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception exception, WebRequest webRequest) {
        emails = configurationUtil.getMessage(Constants.ADMIN_EMAILS);
        BaseResponse response = new BaseResponse();
        response.setResponseCode(Constants.FAILUARE_RESPNSE_CODE);
        response.setResponseMessage(configurationUtil.getMessage(Constants.FAILUARE_RESPNSE_CODE));
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.OK);
        exception.printStackTrace(new PrintWriter(errors));
        errors.toString();
        try {
            mailUtil.sendMailToAdmin(emails,errors);
        } catch (MessagingException e) {
            RetryEmailSending(e,emails,errors);
        }
        return entity;
    }

    @SneakyThrows
    private void RetryEmailSending(MessagingException e, String emails, StringWriter errors) {
        StringWriter whole_error = new StringWriter();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        sw.append((CharSequence) errors);
        whole_error = sw ;
        mailUtil.sendMailToAdmin(emails,whole_error);
    }

}
