package com.oleg.currencygetter.client;

import com.oleg.currencygetter.dto.GifDto;
import feign.RequestLine;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;


@FeignClient(name = "giphy", url = "url")
public interface GiphyApiInterface {
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.IMAGE_GIF_VALUE)
    @RequestLine("GET")
    byte[] getGif(URI url);
}
