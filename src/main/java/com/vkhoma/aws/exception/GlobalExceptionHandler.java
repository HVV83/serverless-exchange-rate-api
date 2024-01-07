package com.vkhoma.aws.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestClientException.class)
    public ProblemDetail handleRestClientException(final RestClientException ex) {
        log.warn("Failed to call external API: [" + ex.getMessage() + "]");
        return ProblemDetail.forStatusAndDetail(INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(NoDataReceivedException.class)
    public ProblemDetail handleNoDataReceivedException(final NoDataReceivedException ex) {
        log.warn("No data received after external API call: [" + ex.getMessage() + "]");
        return ProblemDetail.forStatusAndDetail(BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException ex) {
        log.warn("Invalid request parameter: [" + ex.getMessage() + "]");
        return ProblemDetail.forStatusAndDetail(BAD_REQUEST, ex.getMessage());
    }

}

