package com.olachris.exchangerate;

import com.olachris.exchangerate.adapters.http.ECBDataFetcher;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@TestConfiguration
public class TestConfig {

  @Bean
  @Primary
  public ECBDataFetcher ecbDataFetcher(TestRestTemplate testRestTemplate, Environment environment) {
    String baseUrl = "http://localhost:" + environment.getProperty("wiremock.server.port");
    return new ECBDataFetcher(baseUrl, testRestTemplate.getRestTemplate());
  }
}
