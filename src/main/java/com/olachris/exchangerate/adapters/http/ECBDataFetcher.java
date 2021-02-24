package com.olachris.exchangerate.adapters.http;

import com.olachris.exchangerate.core.ExchangeRate;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

@Service
public class ECBDataFetcher {

  private String urlBase = "https://api.exchangeratesapi.io";
  private UriTemplate getRatesTemplate = new UriTemplate(urlBase + "/{date}?base={base}");
  private final RestTemplate restTemplate;

  @Autowired
  public ECBDataFetcher(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
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
