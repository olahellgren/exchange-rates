package com.olachris.exchangerate.adapters.api;

import com.olachris.exchangerate.adapters.http.ECBDataFetcher;
import com.olachris.exchangerate.core.ExchangeRate;
import java.time.LocalDate;
import java.util.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exchange-rates")
public class ExchangeRateController {

  private final ECBDataFetcher ecbDataFetcher;

  @Autowired
  public ExchangeRateController(ECBDataFetcher ecbDataFetcher) {
    this.ecbDataFetcher = ecbDataFetcher;
  }

  @RequestMapping(value = "/{base}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ExchangeRate getExchangeRate(@PathVariable Currency base, @PathVariable Currency to,
      @RequestParam(required = false) String date) {
    LocalDate localDate;
    if (date == null) {
      localDate = LocalDate.now();
    } else {
      localDate = LocalDate.parse(date);
    }

    return ecbDataFetcher.getAllRatesForDate(base, to, localDate);
  }
}
