package com.oleg.currencygetter.service;

import com.oleg.currencygetter.client.CurrencyApiInterface;
import com.oleg.currencygetter.entity.CurrencyEntity;
import com.oleg.currencygetter.exception.WrongCurrencyCodeException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class CurrencyService {


    private final CurrencyApiInterface currencyApiInterface;

    @Value("${exchangeApi.appId}")
    private String appId;
    @Value("${currency.base}")
    private String baseCurrency;

    public String getPreviousDay(CurrencyEntity currency) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Timestamp timestamp = new Timestamp(currency.getTimestamp()*1000L);
        LocalDate date = timestamp.toLocalDateTime().toLocalDate();
        LocalDate previousDate = date.minusDays(1);
        return formatter.format(previousDate);
    }

    public BigDecimal getCurrencyBasedOn(BigDecimal baseCurrency, BigDecimal value) {
        return baseCurrency.divide(value, 5, RoundingMode.HALF_UP);
    }

    public boolean isUp(String currencyCode) {

        CurrencyEntity currency = currencyApiInterface.getCurrency(appId);
        String date = getPreviousDay(currency);
        CurrencyEntity previousCurrency = currencyApiInterface.getPreviousCurrency(date, appId);

        System.out.println(currency.getRates().getRates().get(currencyCode));

        Optional<BigDecimal> value = Optional.ofNullable(currency
                .getRates()
                .getRates()
                .get(currencyCode));
        if(value.isEmpty()) {throw new WrongCurrencyCodeException(String.format("Currency code %s is not valid",currencyCode));}

        Optional<BigDecimal> prevValue = Optional.ofNullable(previousCurrency
                .getRates()
                .getRates()
                .get(currencyCode));
        if(prevValue.isEmpty()) {throw new WrongCurrencyCodeException(String.format("Currency code %s is not valid",currencyCode));}


        BigDecimal properCurrency =  getCurrencyBasedOn(currency.getRates().getRates().get(baseCurrency)
                ,value.get());
        BigDecimal properPreviousCurrency = getCurrencyBasedOn(previousCurrency.getRates().getRates().get(baseCurrency)
                ,prevValue.get());

        System.out.println(properCurrency);
        System.out.println(properPreviousCurrency);

        return properCurrency.compareTo(properPreviousCurrency) > 0;

    }
}
