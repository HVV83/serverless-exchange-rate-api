package com.vkhoma.aws.service;

import com.vkhoma.aws.model.dto.ExchangeRateDto;
import com.vkhoma.aws.service.impl.MastercardExchangeRateResolver;
import com.vkhoma.aws.service.impl.PrivatbankExchangeRateResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
@Slf4j
@AllArgsConstructor
public class ExchangeRateCalculator {

    private final PrivatbankExchangeRateResolver pbExchangeRate;
    private final MastercardExchangeRateResolver mcExchangeRate;

    public ExchangeRateDto getUahToCzk(LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }

        final CompletableFuture<BigDecimal> mcRateFt = mcExchangeRate.getSaleExchangeRate("CZK", "USD", date);
        final CompletableFuture<BigDecimal> pbRateFt = pbExchangeRate.getSaleExchangeRate("UAH", "USD", date);

        final BigDecimal exchangeRate = Stream.of(mcRateFt, pbRateFt)
                .map(CompletableFuture::join)
                .reduce(BigDecimal.ONE, BigDecimal::multiply).setScale(3, RoundingMode.HALF_UP);

        log.info("Exchange rate between UAH and CZK on {} is {}", date, exchangeRate);
        return ExchangeRateDto.builder()
                .rate(exchangeRate)
                .date(date)
                .build();
    }

}
