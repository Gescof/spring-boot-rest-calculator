package com.gescof.springbootrestcalculator.exceptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@RestControllerAdvice
public class CalculatorControllerAdvisor extends ResponseEntityExceptionHandler {
    private final Clock clock;
    private static final String NO_VALID_FORMAT_MESSAGE = "Input has no valid format";
    private static final String ARITHMETIC_ERROR_MESSAGE = "Error in arithmetic conditions";

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> handleInvalidInputFormat(NumberFormatException ex, WebRequest request) {
        log.warn(String.format("Handling invalid input format for request %s [Input query params: %s]",
                Objects.nonNull(request) ? request.getDescription(true) : "",
                getQueryParamsMapString(Objects.nonNull(request) ? request.getParameterMap() : new HashMap<>())));
        return getConflictResponseEntity(NO_VALID_FORMAT_MESSAGE, ex.getMessage());
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Object> handleArithmeticException(ArithmeticException ex, WebRequest request) {
        log.warn(String.format("Handling arithmetic exception for request %s [Input query params: %s]",
                Objects.nonNull(request) ? request.getDescription(true) : "",
                getQueryParamsMapString(Objects.nonNull(request) ? request.getParameterMap() : new HashMap<>())));
        return getConflictResponseEntity(ARITHMETIC_ERROR_MESSAGE, ex.getMessage());
    }

    private static String getQueryParamsMapString(Map<String, String[]> queryParamsMap) {
        return queryParamsMap.keySet().stream()
                .map(key -> key + "=" + Arrays.toString(queryParamsMap.get(key)))
                .collect(Collectors.joining(", ", "{", "}"));
    }

    private ResponseEntity<Object> getConflictResponseEntity(String message, String errors) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now(clock));
        body.put("status", HttpStatus.CONFLICT.toString());
        body.put("message", message);
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}
