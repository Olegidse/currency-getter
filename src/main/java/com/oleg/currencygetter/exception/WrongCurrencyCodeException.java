package com.oleg.currencygetter.exception;

public class WrongCurrencyCodeException extends RuntimeException {
    public WrongCurrencyCodeException(String message) {
        super(message);
    }
}
