package com.vkhoma.aws.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class ExchangeRateDto {
    BigDecimal rate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    LocalDate date;

}
