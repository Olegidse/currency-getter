package com.oleg.currencygetter.exception;

import com.oleg.currencygetter.entity.ApiErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseBody
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {WrongCurrencyCodeException.class})
    protected ResponseEntity<Object> handleError(WrongCurrencyCodeException ex, WebRequest request) {
        logger.error("Exception is occurred", ex);
        return handleExceptionInternal(ex, ApiErrorResponse.builder()
                .errorCode("VALIDATION_ERROR")
                .errorMessage(ex.getMessage())
                .build(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {NotValidCurrencyCodeException.class})
    protected ResponseEntity<Object> handleNotValidCurrencyCodeError(NotValidCurrencyCodeException ex, WebRequest request) {
        logger.error("Exception is occurred", ex);
        return handleExceptionInternal(ex, ApiErrorResponse.builder()
                .errorCode("VALIDATION_ERROR")
                .errorMessage(ex.getMessage())
                .build(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
