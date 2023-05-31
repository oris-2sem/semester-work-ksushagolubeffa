package org.example.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.client.dto.ExchangeRatesResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExchangeRatesClient {

    private final RestTemplate restTemplate;

    public ExchangeRatesClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ExchangeRatesResponse getExchangeRates() {
        String url = "https://exchange-rates.abstractapi.com/v1/live?api_key={apiKey}&base={base}&target={targets}";
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("apiKey", "4fd28b4251ce411c8479a651c45e46b2");
        uriVariables.put("base", "RUB");
        uriVariables.put("targets", "USD,EUR");
        ResponseEntity<ExchangeRatesResponse> response = restTemplate.getForEntity(url, ExchangeRatesResponse.class, uriVariables);
        ExchangeRatesResponse jsonResponse = response.getBody();
        System.out.println(jsonResponse);
        return response.getBody();
    }
}