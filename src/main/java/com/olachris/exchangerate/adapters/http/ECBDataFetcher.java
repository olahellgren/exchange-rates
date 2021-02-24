package com.olachris.exchangerate.adapters.http;

import com.olachris.exchangerate.core.ExchangeRate;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

public class ECBDataFetcher {

  private UriTemplate getRatesTemplate;
  private final RestTemplate restTemplate;

  @Autowired
  public ECBDataFetcher(String baseUrl, RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
    this.getRatesTemplate = new UriTemplate(baseUrl + "/{date}?base={base}");
  }


  public ExchangeRate getAllRatesForDate(Currency baseCurrency, Currency toCurrency, LocalDate localDate) {
    var ratesForDate = getAllRatesForDate(localDate, baseCurrency);
    return ratesForDate.toExchangeRate(toCurrency);
  }

  public ECBData getAllRatesForDate(LocalDate localDate, Currency baseCurrency) {
    var pathVariables = Map.of(
        "date", localDate.toString(),
        "base", baseCurrency.getCurrencyCode()
    );
    var uri = getRatesTemplate.expand(pathVariables);
    return restTemplate.getForObject(uri, ECBData.class);
  }
}
