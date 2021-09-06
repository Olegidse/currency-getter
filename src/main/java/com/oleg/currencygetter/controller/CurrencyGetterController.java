package com.oleg.currencygetter.controller;

import com.oleg.currencygetter.client.GiphyApiInterface;
import com.oleg.currencygetter.service.GiphyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequiredArgsConstructor
public class CurrencyGetterController {

    private final GiphyApiInterface giphyApiInterface;
    private final GiphyService giphyService;

    @Operation(summary = "Метод для получения gif изображения по коду валюты",
            description = "Код валюты - 3 латинские буквы")
    @GetMapping(value = "/get-gif/{currencyCode}", produces = MediaType.IMAGE_GIF_VALUE)
    public ResponseEntity<Object> downloadGif(@PathVariable String currencyCode) throws URISyntaxException {
        URI gifURI = new URI(giphyService.getGifUri(currencyCode));
        byte[] gifFile = giphyApiInterface.getGif(gifURI);
        return ResponseEntity
                .ok()
                .contentLength(gifFile.length)
                .contentType(MediaType.IMAGE_GIF)
                .body(gifFile);
    }
}

