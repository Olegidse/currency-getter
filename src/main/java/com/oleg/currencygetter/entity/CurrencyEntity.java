package com.oleg.currencygetter.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.oleg.currencygetter.entity.Rates;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Data
@Accessors(chain = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyEntity {
    Boolean success;
    Long timestamp;
    String base;
    Rates rates;
}