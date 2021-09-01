package com.oleg.currencygetter.client;

import com.oleg.currencygetter.entity.CurrencyEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "currency",  url = "${exchangeApi.baseUrl}")
public interface CurrencyApiInterface {

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/latest.json")
    CurrencyEntity getCurrency(@RequestParam String app_id);

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/historical/{date}.json")
    CurrencyEntity getPreviousCurrency(@PathVariable String date, @RequestParam String app_id);
}