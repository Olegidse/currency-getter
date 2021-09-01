package com.oleg.currencygetter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyGetterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyGetterApplication.class, args);
	}

}
