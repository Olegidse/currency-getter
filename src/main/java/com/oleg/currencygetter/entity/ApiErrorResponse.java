package com.oleg.currencygetter.entity;

import lombok.Builder;
import lombok.Getter;

/**
 * Wrapper для ошибок
 */
@Builder
@Getter
public class ApiErrorResponse {
    private String errorCode;
    private String errorMessage;
}
