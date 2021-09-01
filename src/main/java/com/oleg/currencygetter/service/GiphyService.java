package com.oleg.currencygetter.service;

import com.oleg.currencygetter.client.RandomGiphyApiInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiphyService {

    private final RandomGiphyApiInterface randomGiphyApiInterface;
    private final CurrencyService currencyService;

    @Value("${giphyKey}")
    private String apiKey;
    @Value("${giphy.richTag}")
    private String richTag;
    @Value("${giphy.brokeTag}")
    private String brokeTag;

    public String getRichGifUri() {
        return randomGiphyApiInterface.getGif(apiKey, richTag, "g" ).getData().getImages().getOriginal().getWebp();
    }

    public String getBrokeGifUri() {
        return randomGiphyApiInterface.getGif(apiKey, brokeTag, "g" ).getData().getImages().getOriginal().getWebp();
    }

    public String getGifUri(String currencyCode) {
        if (currencyService.isUp(currencyCode)) {return getRichGifUri();}
        return getBrokeGifUri();
    }
}
