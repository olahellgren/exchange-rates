package com.olachris.exchangerate.core;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
public class ExchangeRate {

  LocalDate date;
  Rate base;
  Rate to;

  @Data
  @AllArgsConstructor
  public static class Rate {

    Currency currency;
    BigDecimal value;
  }
}

