package com.vkhoma.aws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MastercardExchangeRate {

    private Data data;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @ToString
    @Builder(toBuilder = true)
    public static class Data {
        private String conversionRate;
    }

}
