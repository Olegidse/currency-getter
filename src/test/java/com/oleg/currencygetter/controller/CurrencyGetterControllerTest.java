package com.oleg.currencygetter.controller;

import com.oleg.currencygetter.client.GiphyApiInterface;
import com.oleg.currencygetter.controller.CurrencyGetterController;
import com.oleg.currencygetter.service.GiphyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyGetterController.class)
public class CurrencyGetterControllerTest {
    @MockBean
    private GiphyService giphyService;
    @MockBean
    private GiphyApiInterface giphyApiInterface;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testReturn200() throws Exception {

        Path path = Path.of("src/main/resources/giffInBytesTest");
        byte[] gif = Files.readAllBytes(path);
        when(giphyService.getGifUri(any())).thenReturn("");
        when(giphyApiInterface.getGif(any())).thenReturn(gif);

        mockMvc.perform(get("/get-gif/{currencyCode}", "USD"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.IMAGE_GIF));
    }
}