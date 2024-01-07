package com.vkhoma.aws.service.impl;

import com.vkhoma.aws.exception.NoDataReceivedException;
import com.vkhoma.aws.model.PrivatbankExchangeRate;
import com.vkhoma.aws.service.AsyncExchangeRateResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PrivatbankExchangeRateResolver extends AsyncExchangeRateResolver {

    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Value("${api.url-pattern.privatbank}")
    private String URL_PATTERN;
    private final RestTemplate restTemplate;

    @Override
    public BigDecimal retrieveExchangeRate(final String baseCurrency, final String currency, final LocalDate date) {
        final String url = URL_PATTERN.formatted(DATE_FORMATTER.format(date));
        final var response = restTemplate.exchange(url, HttpMethod.GET, null, PrivatbankExchangeRate.class);
        final PrivatbankExchangeRate exchangeRateResponse = response.getBody();

        if (response.getStatusCode().is2xxSuccessful() && exchangeRateResponse != null && exchangeRateResponse.getExchangeRates() != null) {
            for (var exRate : exchangeRateResponse.getExchangeRates()) {
                if (exRate.getBaseCurrency().equals(baseCurrency) && exRate.getCurrency().equals(currency)) {
                    return new BigDecimal(exRate.getSaleRate());
                }
            }
        }
        throw new NoDataReceivedException("Privatbank - no data received for exchange rate between %s and %s on %s".formatted(baseCurrency, currency, date));
    }

}
