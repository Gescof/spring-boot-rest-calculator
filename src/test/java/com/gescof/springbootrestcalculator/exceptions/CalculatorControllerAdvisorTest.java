package com.gescof.springbootrestcalculator.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Map;

class CalculatorControllerAdvisorTest {
    private final static LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(
            LocalDate.of(2021, 10, 23),
            LocalTime.of(12, 35, 59, 0)
    );

    @InjectMocks
    private CalculatorControllerAdvisor calculatorControllerAdvisor;

    @Mock
    private Clock mockedClock;

    private Clock fixedClock;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        fixedClock = Clock.fixed(LOCAL_DATE_TIME.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        Mockito.doReturn(fixedClock.instant()).when(mockedClock).instant();
        Mockito.doReturn(fixedClock.getZone()).when(mockedClock).getZone();
    }

    @Test
    void testShouldHandleInvalidInputFormatReturningHttpStatus409() {
        Map<String, Object> expectedResultBody = Map.of(
                "timestamp", LocalDateTime.now(fixedClock),
                "status", HttpStatus.CONFLICT.toString(),
                "message", "Input has no valid format",
                "errors", new NumberFormatException("Detail message").getMessage());
        ResponseEntity<Object> invalidInputFormatResult = calculatorControllerAdvisor.handleInvalidInputFormat(
                new NumberFormatException("Detail message"),
                null
        );
        Assertions.assertEquals(
                new ResponseEntity<>(expectedResultBody, HttpStatus.CONFLICT),
                invalidInputFormatResult
        );
    }

    @Test
    void testShouldHandleArithmeticExceptionReturningHttpStatus409() {
        Map<String, Object> expectedResultBody = Map.of(
                "timestamp", LocalDateTime.now(fixedClock),
                "status", HttpStatus.CONFLICT.toString(),
                "message", "Error in arithmetic conditions",
                "errors", new ArithmeticException("Detail message").getMessage());
        ResponseEntity<Object> arithmeticExceptionResult = calculatorControllerAdvisor.handleArithmeticException(
                new ArithmeticException("Detail message"),
                null
        );
        Assertions.assertEquals(
                new ResponseEntity<>(expectedResultBody, HttpStatus.CONFLICT),
                arithmeticExceptionResult
        );
    }
}
