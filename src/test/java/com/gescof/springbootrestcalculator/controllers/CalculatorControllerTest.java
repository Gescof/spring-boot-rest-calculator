package com.gescof.springbootrestcalculator.controllers;

import com.gescof.springbootrestcalculator.models.InputDto;
import com.gescof.springbootrestcalculator.models.OutputDto;
import com.gescof.springbootrestcalculator.models.enums.OperationType;
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
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimal)
                .secondDecimal(secondDecimal)
                .build();
        OutputDto expectedOutputDto = OutputDto.builder()
                .operationType(OperationType.SUM)
                .firstDecimal(new BigDecimal(firstDecimal))
                .secondDecimal(new BigDecimal(secondDecimal))
                .result(BigDecimal.valueOf(9))
                .build();
        Mockito.when(mockedCalculatorService.getSumResult(Mockito.any()))
                .thenReturn(expectedOutputDto);

        ResponseEntity<OutputDto> sumResult = calculatorController.getSumResult(inputDto);
        Mockito.verify(mockedCalculatorService, Mockito.times(1))
                .getSumResult(Mockito.any());
        Assertions.assertEquals(
                ResponseEntity.ok(expectedOutputDto),
                sumResult
        );
    }

    @Test
    void testShouldGetSubtractionResultWithHttpStatus20O() {
        String firstDecimal = "-11";
        String secondDecimal = "3";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimal)
                .secondDecimal(secondDecimal)
                .build();
        OutputDto expectedOutputDto = OutputDto.builder()
                .operationType(OperationType.SUBTRACTION)
                .firstDecimal(new BigDecimal(firstDecimal))
                .secondDecimal(new BigDecimal(secondDecimal))
                .result(BigDecimal.valueOf(-14))
                .build();
        Mockito.when(mockedCalculatorService.getSubtractionResult(Mockito.any()))
                .thenReturn(expectedOutputDto);

        ResponseEntity<OutputDto> subtractionResult = calculatorController.getSubtractionResult(inputDto);
        Mockito.verify(mockedCalculatorService, Mockito.times(1))
                .getSubtractionResult(Mockito.any());
        Assertions.assertEquals(
                ResponseEntity.ok(expectedOutputDto),
                subtractionResult
        );
    }

    @Test
    void testShouldGetMultiplicationResultWithHttpStatus20O() {
        String firstDecimal = "20";
        String secondDecimal = "1";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimal)
                .secondDecimal(secondDecimal)
                .build();
        OutputDto expectedOutputDto = OutputDto.builder()
                .operationType(OperationType.MULTIPLICATION)
                .firstDecimal(new BigDecimal(firstDecimal))
                .secondDecimal(new BigDecimal(secondDecimal))
                .result(BigDecimal.valueOf(20))
                .build();
        Mockito.when(mockedCalculatorService.getMultiplicationResult(Mockito.any()))
                .thenReturn(expectedOutputDto);

        ResponseEntity<OutputDto> multiplicationResult = calculatorController.getMultiplicationResult(inputDto);
        Mockito.verify(mockedCalculatorService, Mockito.times(1))
                .getMultiplicationResult(Mockito.any());
        Assertions.assertEquals(
                ResponseEntity.ok(expectedOutputDto),
                multiplicationResult
        );
    }

    @Test
    void testShouldGetDivisionResultWithHttpStatus20O() {
        String firstDecimal = "15";
        String secondDecimal = "5";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimal)
                .secondDecimal(secondDecimal)
                .build();
        OutputDto expectedOutputDto = OutputDto.builder()
                .operationType(OperationType.DIVISION)
                .firstDecimal(new BigDecimal(firstDecimal))
                .secondDecimal(new BigDecimal(secondDecimal))
                .result(BigDecimal.valueOf(3))
                .build();
        Mockito.when(mockedCalculatorService.getDivisionResult(Mockito.any()))
                .thenReturn(expectedOutputDto);

        ResponseEntity<OutputDto> divisionResult = calculatorController.getDivisionResult(inputDto);
        Mockito.verify(mockedCalculatorService, Mockito.times(1))
                .getDivisionResult(Mockito.any());
        Assertions.assertEquals(
                ResponseEntity.ok(expectedOutputDto),
                divisionResult
        );
    }
}
