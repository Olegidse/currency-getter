package com.oleg.currencygetter.controller;

import com.oleg.currencygetter.client.GiphyApiInterface;
import com.oleg.currencygetter.client.RandomGiphyApiInterface;
import com.oleg.currencygetter.service.GiphyService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequiredArgsConstructor
@Import(FeignClientsConfiguration.class)
public class CurrencyGetterController {

    private final GiphyApiInterface giphyApiInterface;
    private final GiphyService giphyService;
    private final RandomGiphyApiInterface randomGiphyApiInterface;

    @GetMapping(value = "/get-gif/{currencyCode}", produces = MediaType.IMAGE_GIF_VALUE)
    public ResponseEntity downloadGif(@PathVariable Long currencyCode) throws URISyntaxException {
        URI gifURI = new URI(giphyService.getGifUri());
        byte[] gifFile = giphyApiInterface.getGif(gifURI);
        return ResponseEntity
                .ok()
                .contentLength(gifFile.length)
                .contentType(MediaType.IMAGE_GIF)
                .body(gifFile);
    }
}

