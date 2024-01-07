package com.vkhoma.aws.service;

import org.springframework.scheduling.annotation.Async;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

public abstract class AsyncExchangeRateResolver {

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<BigDecimal> getSaleExchangeRate(final String baseCurrency, final String currency, final LocalDate date) {
        final BigDecimal exchangeRate = retrieveExchangeRate(baseCurrency, currency, date);
        return CompletableFuture.completedFuture(exchangeRate);
    }

    /**
     * Returns exchange rate between base currency and currency on specified date
     *
     * @param baseCurrency currency from which exchange rate is retrieved
     * @param currency     currency to which exchange rate is retrieved
     * @param date         date for which exchange rate is retrieved (if null, current date is used)
     * @return exchange rate between base currency and currency on specified date
     */
    protected abstract BigDecimal retrieveExchangeRate(String baseCurrency, String currency, LocalDate date);

}
