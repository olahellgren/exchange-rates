package com.olachris.exchangerate.adapters.http;

import java.time.LocalDate;
import java.util.Currency;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ECBDataFetcherTest {

  @Autowired
  ECBDataFetcher eCBDataFetcher;

  @Test
  void getData() {
    var ratesForDate = eCBDataFetcher.getAllRatesForDate(LocalDate.parse("2021-02-22"), Currency.getInstance("EUR"));
    Assertions.assertThat(ratesForDate.getBase()).isEqualTo(Currency.getInstance("EUR"));
    Assertions.assertThat(ratesForDate.getDate()).isEqualTo(LocalDate.parse("2021-02-22"));
  }
}