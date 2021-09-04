package com.oleg.currencygetter.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.*;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class Rates {

   private Map<String, BigDecimal> rates = new HashMap<>();

   @JsonAnySetter
   public void setRates(String name, BigDecimal value) {
      this.rates.put(name, value);
   }
}