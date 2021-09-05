package com.oleg.currencygetter.exception;

public class NotValidCurrencyCodeException extends RuntimeException {
    public NotValidCurrencyCodeException(String message) {
        super(message);
    }
}
