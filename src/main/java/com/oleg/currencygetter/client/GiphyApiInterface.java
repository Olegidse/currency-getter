package com.oleg.currencygetter.client;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

@FeignClient(name = "giphy", url = "url")
public interface GiphyApiInterface {
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.IMAGE_GIF_VALUE)
    @RequestLine("GET")
    byte[] getGif(URI url);
}
