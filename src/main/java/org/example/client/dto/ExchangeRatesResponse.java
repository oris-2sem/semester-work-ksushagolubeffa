package org.example.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ExchangeRatesResponse {
    private String base;
    private Integer last_updated;
    @JsonProperty("exchange_rates")
    private Map<String, Double> rates;
}
