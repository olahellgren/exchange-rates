package com.olachris.exchangerate;

import com.olachris.exchangerate.adapters.http.ECBDataFetcher;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.build();
  }

  @Bean
  public ECBDataFetcher ecbDataFetcher(RestTemplate restTemplate) {
     String baseUrl = "https://api.exchangeratesapi.io";
     return new ECBDataFetcher(baseUrl, restTemplate);
  }
}
