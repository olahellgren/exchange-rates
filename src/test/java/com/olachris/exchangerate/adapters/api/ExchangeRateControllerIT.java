package com.olachris.exchangerate.adapters.api;

import static org.junit.jupiter.api.Assertions.*;

import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.path.json.config.JsonPathConfig.NumberReturnType;
import java.math.BigDecimal;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ExchangeRateControllerIT {
  @LocalServerPort
  private int localServerPort;
  @BeforeEach
  void setUp() {
    RestAssured.config = RestAssured.config()
        .jsonConfig(
            JsonConfig.jsonConfig()
                .numberReturnType(NumberReturnType.BIG_DECIMAL)
        );
  }
  @Test
  void getRates() {
    RestAssured.given()
        .port(localServerPort)
        .get("/api/exchange-rates/EUR/SEK?date=2021-02-22")
        .then()
        .assertThat()
        .body("base.currency", Matchers.is("EUR"))
        .body("base.value", Matchers.is(1))
        .body("to.currency", Matchers.is("SEK"))
        .body("to.value", Matchers.is(new BigDecimal("10.0315")))
        .body("date", Matchers.is("2021-02-22"));
  }
}