package com.olachris.exchangerate.adapters.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Currency;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.core.io.Resource;

@JsonTest
class ECBDataTest {

  @Autowired
  ObjectMapper objectMapper;
  @Value("classpath:latest-base-eur.json")
  Resource latestBaseEURResource;
  ECBData ecbData;

  @BeforeEach
  void setUp() throws IOException {
    ecbData = objectMapper.readValue(latestBaseEURResource.getInputStream(), ECBData.class);
  }

  @Test
  void testMapECBData() {
    Assertions.assertThat(ecbData.getDate()).isEqualTo("2021-02-22");
    Assertions.assertThat(ecbData.getBase()).isEqualTo(Currency.getInstance("EUR"));
    Assertions.assertThat(ecbData.getRates()).hasSize(32);
  }

  @Test
  void testThatInvalidCurreciesAreNotAllowed() {
    var ecbDataString = "{"
        + "\"rates\": "
        + "  { \"YYY\": 1.2345 },"
        + "\"base\":\"XXX\", "
        + "\"date\":\"2020-01-01\""
        + "}";
    Assertions.assertThatThrownBy(() ->
        ecbData = objectMapper.readValue(ecbDataString, ECBData.class)
    )
        .isInstanceOf(JsonProcessingException.class);
  }
}