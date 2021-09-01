package com.oleg.currencygetter.client;

import com.oleg.currencygetter.entity.gif.GeneralResponse;
import com.oleg.currencygetter.entity.gif.GifEntity;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "randomGif", url = "${giphy.baseUrl}")
public interface RandomGiphyApiInterface {

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    GeneralResponse getGif(@RequestParam String api_key, @RequestParam String tag, @RequestParam String rating);

}
