package com.gescof.springbootrestcalculator.services;

import com.gescof.springbootrestcalculator.model.ResultDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

class CalculatorServiceTest {
    @InjectMocks
    private CalculatorService calculatorService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShouldGetSumResultReturningResultDTO() {
        String firstDecimal = "2";
        String secondDecimal = "7";
        ResultDTO expectedResultDTO = ResultDTO.builder()
                .firstDecimal(new BigDecimal(firstDecimal))
                .secondDecimal(new BigDecimal(secondDecimal))
                .result(BigDecimal.valueOf(9))
                .build();

        ResultDTO sumResult = calculatorService.getSumResult(firstDecimal, secondDecimal);
        Assertions.assertEquals(
                expectedResultDTO,
                sumResult
        );
    }

    @Test
    void testShouldGetSumResultThrowingNumberFormatException() {
        String firstDecimal = "invalid_number";
        String secondDecimal = "7";

        Assertions.assertThrows(
                NumberFormatException.class, () -> calculatorService.getSumResult(firstDecimal, secondDecimal)
        );
    }

    @Test
    void testShouldGetSubtractionResultReturningResultDTO() {
        String firstDecimal = "-11";
        String secondDecimal = "3";
        ResultDTO expectedResultDTO = ResultDTO.builder()
                .firstDecimal(new BigDecimal(firstDecimal))
                .secondDecimal(new BigDecimal(secondDecimal))
                .result(BigDecimal.valueOf(-14))
                .build();

        ResultDTO subtractionResult = calculatorService.getSubtractionResult(firstDecimal, secondDecimal);
        Assertions.assertEquals(
                expectedResultDTO,
                subtractionResult
        );
    }

    @Test
    void testShouldGetSubtractionResultThrowingNumberFormatException() {
        String firstDecimal = "-11";
        String secondDecimal = "invalid_number";

        Assertions.assertThrows(
                NumberFormatException.class, () -> calculatorService.getSubtractionResult(firstDecimal, secondDecimal)
        );
    }

    @Test
    void testShouldGetMultiplicationResultReturningResultDTO() {
        String firstDecimal = "20";
        String secondDecimal = "1";
        ResultDTO expectedResultDTO = ResultDTO.builder()
                .firstDecimal(new BigDecimal(firstDecimal))
                .secondDecimal(new BigDecimal(secondDecimal))
                .result(BigDecimal.valueOf(20))
                .build();

        ResultDTO multiplicationResult = calculatorService.getMultiplicationResult(firstDecimal, secondDecimal);
        Assertions.assertEquals(
                expectedResultDTO,
                multiplicationResult
        );
    }

    @Test
    void testShouldGetMultiplicationResultThrowingNumberFormatException() {
        String firstDecimal = "invalid_number";
        String secondDecimal = "invalid_number";

        Assertions.assertThrows(
                NumberFormatException.class, () -> calculatorService.getMultiplicationResult(firstDecimal, secondDecimal)
        );
    }

    @Test
    void testShouldGetDivisionResultReturningResultDTO() {
        String firstDecimal = "15";
        String secondDecimal = "5";
        ResultDTO expectedResultDTO = ResultDTO.builder()
                .firstDecimal(new BigDecimal(firstDecimal))
                .secondDecimal(new BigDecimal(secondDecimal))
                .result(BigDecimal.valueOf(3))
                .build();

        ResultDTO divisionResult = calculatorService.getDivisionResult(firstDecimal, secondDecimal);
        Assertions.assertEquals(
                expectedResultDTO,
                divisionResult
        );
    }

    @Test
    void testShouldGetDivisionResultThrowingNumberFormatException() {
        String firstDecimal = "";
        String secondDecimal = " ";

        Assertions.assertThrows(
                NumberFormatException.class, () -> calculatorService.getDivisionResult(firstDecimal, secondDecimal)
        );
    }

    @Test
    void testShouldGetDivisionResultThrowingArithmeticException() {
        String firstDecimal = "9";
        String secondDecimal = "0";

        Assertions.assertThrows(
                ArithmeticException.class, () -> calculatorService.getDivisionResult(firstDecimal, secondDecimal)
        );
    }
}
