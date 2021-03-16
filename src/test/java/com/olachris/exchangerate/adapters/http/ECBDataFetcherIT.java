package com.olachris.exchangerate.adapters.http;

import com.olachris.exchangerate.TestConfig;
import java.time.LocalDate;
import java.util.Currency;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

@SpringBootTest(
    webEnvironment = WebEnvironment.RANDOM_PORT,
    classes = {TestConfig.class}
    )
@AutoConfigureWireMock(port = 0)
class ECBDataFetcherIT {

  @Autowired
  ECBDataFetcher ecbDataFetcher;

  @Test
  void getData() {
    var ratesForDate = ecbDataFetcher.getAllRatesForDate(LocalDate.parse("2021-02-22"), Currency.getInstance("EUR"));
    Assertions.assertThat(ratesForDate.getBase()).isEqualTo(Currency.getInstance("EUR"));
    Assertions.assertThat(ratesForDate.getDate()).isEqualTo(LocalDate.parse("2021-02-22"));
  }
}
