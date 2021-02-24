package com.olachris.exchangerate.adapters.http;

import com.olachris.exchangerate.core.ExchangeRate;
import com.olachris.exchangerate.core.ExchangeRate.Rate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Map;
import lombok.Value;

@Value
public class ECBData {

  Map<Currency, BigDecimal> rates;
  Currency base;
  LocalDate date;

  public ExchangeRate toExchangeRate(Currency toCurrency) {
    return ExchangeRate.builder()
        .date(this.getDate())
        .to(new Rate(toCurrency, this.getRates().get(toCurrency)))
        .base(new Rate(this.getBase(), BigDecimal.ONE))
        .build();
  }
}
