package com.oleg.currencygetter.service;

import com.oleg.currencygetter.client.CurrencyApiInterface;
import com.oleg.currencygetter.entity.CurrencyEntity;
import com.oleg.currencygetter.entity.Rates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class CurrencyServiceTest {

    CurrencyApiInterface currencyApiInterface = Mockito.mock(CurrencyApiInterface.class);

    private CurrencyService currencyService = new CurrencyService(currencyApiInterface);



    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void getPreviousDay() {
        long timestamp = 1630759625L;
        CurrencyEntity currency = new CurrencyEntity();
        currency.setTimestamp(timestamp);

        String actual = "2021-09-03";
        String expected = currencyService.getPreviousDay(currency);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    void getCurrencyBasedOn() {
        BigDecimal baseCurrency = new BigDecimal("75.87");
        BigDecimal value = new BigDecimal("1.14");

        BigDecimal expected = new BigDecimal("66.55263").setScale(5,RoundingMode.HALF_UP);
        BigDecimal actual = currencyService.getCurrencyBasedOn(baseCurrency, value);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void isUpTrue() {

        currencyService.setBaseCurrency("RUB");

        Map<String, BigDecimal> ratesMap = new HashMap<>();
        BigDecimal rub = new BigDecimal("75.87");
        BigDecimal eur = new BigDecimal("0.85");

        ratesMap.put("RUB", rub);
        ratesMap.put("EUR", eur);

        Map<String, BigDecimal> prevRatesMap = new HashMap<>();
        BigDecimal rub1 = new BigDecimal("76.5");
        BigDecimal eur1 = new BigDecimal("0.9");

        prevRatesMap.put("RUB", rub1);
        prevRatesMap.put("EUR", eur1);

        System.out.println(ratesMap);

        Rates rates = new Rates();
        rates = rates.setRates(ratesMap);
        Rates prevRates = new Rates();
        prevRates = prevRates.setRates(prevRatesMap);

        System.out.println(rates);

        CurrencyEntity currency = new CurrencyEntity()
                .setBase("USD")
                .setTimestamp(1630759625L)
                .setRates(rates);

        CurrencyEntity previousCurrency = new CurrencyEntity()
                .setBase("USD")
                .setTimestamp(1630759625L)
                .setRates(prevRates);

        when(currencyApiInterface.getCurrency(any())).thenReturn(currency);
        when(currencyApiInterface.getPreviousCurrency(any(), any())).thenReturn(previousCurrency);


        Boolean actual = currencyService.isUp("EUR");

        Assertions.assertEquals(true, actual);

    }
    @Test
    void isUpFalse() {

        currencyService.setBaseCurrency("RUB");

        Map<String, BigDecimal> ratesMap = new HashMap<>();
        BigDecimal rub = new BigDecimal("76.5");
        BigDecimal eur = new BigDecimal("0.9");

        ratesMap.put("RUB", rub);
        ratesMap.put("EUR", eur);

        Map<String, BigDecimal> prevRatesMap = new HashMap<>();
        BigDecimal rub1 = new BigDecimal("75.87");
        BigDecimal eur1 = new BigDecimal("0.85");

        prevRatesMap.put("RUB", rub1);
        prevRatesMap.put("EUR", eur1);

        System.out.println(ratesMap);

        Rates rates = new Rates();
        rates = rates.setRates(ratesMap);
        Rates prevRates = new Rates();
        prevRates = prevRates.setRates(prevRatesMap);

        System.out.println(rates);

        CurrencyEntity currency = new CurrencyEntity()
                .setBase("USD")
                .setTimestamp(1630759625L)
                .setRates(rates);

        CurrencyEntity previousCurrency = new CurrencyEntity()
                .setBase("USD")
                .setTimestamp(1630759625L)
                .setRates(prevRates);

        when(currencyApiInterface.getCurrency(any())).thenReturn(currency);
        when(currencyApiInterface.getPreviousCurrency(any(), any())).thenReturn(previousCurrency);


        Boolean actual = currencyService.isUp("EUR");

        Assertions.assertEquals(false, actual);

    }

}

