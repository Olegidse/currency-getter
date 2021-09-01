package com.oleg.currencygetter.client;

import com.oleg.currencygetter.dto.GifDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "giphy", url ="https://media1.giphy.com/media/lptjRBxFKCJmFoibP3/giphy.gif?cid=790b761139e4a8a39b48bcf9da6ed237bb9e39fad999b3b5&rid=giphy.gif&ct=g")
public interface GiphyClient {
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.IMAGE_GIF_VALUE)
    byte[] getGif();
}
