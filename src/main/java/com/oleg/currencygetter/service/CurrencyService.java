package com.oleg.currencygetter.service;

import com.oleg.currencygetter.client.CurrencyApiInterface;
import com.oleg.currencygetter.entity.CurrencyEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CurrencyService {


    private final CurrencyApiInterface currencyApiInterface;

    @Value("${exchangeApi.appId}")
    private String appId;

    private Date timestampToDateConverter(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }

    private String getPreviousDay(CurrencyEntity currency) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Timestamp timestamp = new Timestamp(currency.getTimestamp()*1000L);
        LocalDate date = timestamp.toLocalDateTime().toLocalDate();
        LocalDate previousDate = date.minusDays(1);
        return formatter.format(previousDate);
    }
    public boolean isUp() {

        CurrencyEntity currency = currencyApiInterface.getCurrency(appId);
        String date = getPreviousDay(currency);
        CurrencyEntity previousCurrency = currencyApiInterface.getPreviousCurrency(date, appId);
        System.out.println(currency.getRates().getRub());
        System.out.println(previousCurrency.getRates().getRub());
        return true;
    }
}
