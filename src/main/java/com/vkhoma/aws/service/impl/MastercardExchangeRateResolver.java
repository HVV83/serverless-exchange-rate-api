package com.vkhoma.aws.service.impl;

import com.vkhoma.aws.exception.NoDataReceivedException;
import com.vkhoma.aws.model.MastercardExchangeRate;
import com.vkhoma.aws.service.AsyncExchangeRateResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MastercardExchangeRateResolver extends AsyncExchangeRateResolver {

    @Value("${api.url-pattern.mastercard}")
    private String URL_PATTERN;
    private final RestTemplate restTemplate;

    @Override
    public BigDecimal retrieveExchangeRate(final String baseCurrency, final String currency, final LocalDate date) {
        final String url = URL_PATTERN.formatted(date, baseCurrency, currency);
        final var response = restTemplate.exchange(url, HttpMethod.GET, null, MastercardExchangeRate.class);
        final MastercardExchangeRate exchangeRateResponse = response.getBody();

        if (response.getStatusCode().is2xxSuccessful() &&
                exchangeRateResponse != null &&
                exchangeRateResponse.getData().getConversionRate() != null) {
            return new BigDecimal(exchangeRateResponse.getData().getConversionRate());
        }
        throw new NoDataReceivedException("Mastercard - no data received for exchange rate between %s and %s on %s".formatted(baseCurrency, currency, date));
    }

}
