package com.gescof.springbootrestcalculator.controllers;

import com.gescof.springbootrestcalculator.model.ResultDTO;
import com.gescof.springbootrestcalculator.services.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

class CalculatorControllerTest {
    @InjectMocks
    private CalculatorController calculatorController;
    @Mock
    private CalculatorService mockedCalculatorService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShouldGetSumResultWithHttpStatus20O() {
        String firstDecimal = "2";
        String secondDecimal = "7";
        ResultDTO expectedResultDTO = ResultDTO.builder()
                .firstDecimal(new BigDecimal(firstDecimal))
                .secondDecimal(new BigDecimal(secondDecimal))
                .result(BigDecimal.valueOf(9))
                .build();
        Mockito.when(mockedCalculatorService.getSumResult(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(expectedResultDTO);

        ResponseEntity<ResultDTO> sumResult = calculatorController.getSumResult(firstDecimal, secondDecimal);
        Mockito.verify(mockedCalculatorService, Mockito.times(1))
                .getSumResult(Mockito.anyString(), Mockito.anyString());
        Assertions.assertEquals(
                ResponseEntity.ok(expectedResultDTO),
                sumResult
        );
    }

    @Test
    void testShouldGetSubtractionResultWithHttpStatus20O() {
        String firstDecimal = "-11";
        String secondDecimal = "3";
        ResultDTO expectedResultDTO = ResultDTO.builder()
                .firstDecimal(new BigDecimal(firstDecimal))
                .secondDecimal(new BigDecimal(secondDecimal))
                .result(BigDecimal.valueOf(-14))
                .build();
        Mockito.when(mockedCalculatorService.getSubtractionResult(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(expectedResultDTO);

        ResponseEntity<ResultDTO> subtractionResult = calculatorController.getSubtractionResult(firstDecimal, secondDecimal);
        Mockito.verify(mockedCalculatorService, Mockito.times(1))
                .getSubtractionResult(Mockito.anyString(), Mockito.anyString());
        Assertions.assertEquals(
                ResponseEntity.ok(expectedResultDTO),
                subtractionResult
        );
    }

    @Test
    void testShouldGetMultiplicationResultWithHttpStatus20O() {
        String firstDecimal = "20";
        String secondDecimal = "1";
        ResultDTO expectedResultDTO = ResultDTO.builder()
                .firstDecimal(new BigDecimal(firstDecimal))
                .secondDecimal(new BigDecimal(secondDecimal))
                .result(BigDecimal.valueOf(20))
                .build();
        Mockito.when(mockedCalculatorService.getMultiplicationResult(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(expectedResultDTO);

        ResponseEntity<ResultDTO> multiplicationResult = calculatorController.getMultiplicationResult(firstDecimal, secondDecimal);
        Mockito.verify(mockedCalculatorService, Mockito.times(1))
                .getMultiplicationResult(Mockito.anyString(), Mockito.anyString());
        Assertions.assertEquals(
                ResponseEntity.ok(expectedResultDTO),
                multiplicationResult
        );
    }

    @Test
    void testShouldGetDivisionResultWithHttpStatus20O() {
        String firstDecimal = "15";
        String secondDecimal = "5";
        ResultDTO expectedResultDTO = ResultDTO.builder()
                .firstDecimal(new BigDecimal(firstDecimal))
                .secondDecimal(new BigDecimal(secondDecimal))
                .result(BigDecimal.valueOf(3))
                .build();
        Mockito.when(mockedCalculatorService.getDivisionResult(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(expectedResultDTO);

        ResponseEntity<ResultDTO> divisionResult = calculatorController.getDivisionResult(firstDecimal, secondDecimal);
        Mockito.verify(mockedCalculatorService, Mockito.times(1))
                .getDivisionResult(Mockito.anyString(), Mockito.anyString());
        Assertions.assertEquals(
                ResponseEntity.ok(expectedResultDTO),
                divisionResult
        );
    }
}
