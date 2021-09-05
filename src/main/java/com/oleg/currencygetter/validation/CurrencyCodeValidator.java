package com.oleg.currencygetter.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class CurrencyCodeValidator {

        private static final Pattern phonePattern =
                Pattern.compile("[A-Z]{3}");

        @Value("${currency.base}")
        private String baseCurrency;

        public boolean isValid(String currencyCode) {
            return currencyCode != null
                    && phonePattern.matcher(currencyCode).matches()
                    && !currencyCode.equals(baseCurrency);
        }

}
