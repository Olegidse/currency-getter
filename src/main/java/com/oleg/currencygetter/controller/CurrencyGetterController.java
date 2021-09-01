package com.oleg.currencygetter.controller;

import com.oleg.currencygetter.client.GiphyClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CurrencyGetterController {

    private final GiphyClient giphyClient;

    @GetMapping(value = "/get-gif/{currencyCode}", produces = MediaType.IMAGE_GIF_VALUE)
    public ResponseEntity downloadGif(@PathVariable Long currencyCode)  {
        byte[] gifFile = giphyClient.getGif();
        return ResponseEntity
                .ok()
                .contentLength(gifFile.length)
                .contentType(MediaType.IMAGE_GIF)
                .body(gifFile);
    }
}

