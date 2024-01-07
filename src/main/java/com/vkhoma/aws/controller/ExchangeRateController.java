package com.vkhoma.aws.controller;

import com.vkhoma.aws.model.dto.ExchangeRateDto;
import com.vkhoma.aws.service.ExchangeRateCalculator;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/rate")
@AllArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateCalculator exchangeRateCalculator;

    @GetMapping(value = "/uahToCzk", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExchangeRateDto> getUahToCzkRate(
            @RequestParam(required = false) @DateTimeFormat(pattern = "d.M.yyyy") LocalDate date) {
        return ResponseEntity.ok(exchangeRateCalculator.getUahToCzk(date));
    }

}
