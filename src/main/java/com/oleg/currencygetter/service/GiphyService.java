package com.oleg.currencygetter.service;

import com.oleg.currencygetter.client.RandomGiphyApiInterface;
import com.oleg.currencygetter.exception.NotValidCurrencyCodeException;
import com.oleg.currencygetter.validation.CurrencyCodeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiphyService {

    private final RandomGiphyApiInterface randomGiphyApiInterface;
    private final CurrencyService currencyService;
    private final CurrencyCodeValidator currencyCodeValidator;

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
        String currencyCodeUpper = currencyCode.toUpperCase();
        if(!currencyCodeValidator.isValid(currencyCodeUpper)) {
            throw new NotValidCurrencyCodeException("Currency code must be 3 latin letters and not equal to base currency");
        }
        if (currencyService.isUp(currencyCodeUpper)) {return getRichGifUri();}
        return getBrokeGifUri();
    }
}
