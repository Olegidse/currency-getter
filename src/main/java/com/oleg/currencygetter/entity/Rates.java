package com.oleg.currencygetter.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class Rates {
    @JsonProperty("RUB")
    BigDecimal rub;
    @JsonProperty("EUR")
    BigDecimal eur;
    @JsonProperty("CHF")
    BigDecimal chf;
}