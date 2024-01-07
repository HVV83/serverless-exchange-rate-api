package com.vkhoma.aws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrivatbankExchangeRate {

    @JsonSetter("exchangeRate")
    private List<ExchangeRate> exchangeRates;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @ToString
    @Builder(toBuilder = true)
    public static class ExchangeRate {
        private String baseCurrency;
        private String currency;
        private String saleRateNB;
        private String purchaseRateNB;
        private String saleRate;
        private String purchaseRate;

    }

}
