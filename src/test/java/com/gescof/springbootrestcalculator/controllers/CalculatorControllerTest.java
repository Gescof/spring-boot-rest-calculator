package com.gescof.springbootrestcalculator.controllers;

import com.gescof.springbootrestcalculator.models.InputDto;
import com.gescof.springbootrestcalculator.models.OutputDto;
import com.gescof.springbootrestcalculator.models.OutputElementDto;
import com.gescof.springbootrestcalculator.models.enums.OperationType;
import com.gescof.springbootrestcalculator.services.CalculatorService;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

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
    void testShouldMakeSumOperationWithHttpStatus20O() {
        String firstDecimalStr = "2";
        String secondDecimalStr = "7";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();
        OutputElementDto expectedOutputElementDto = OutputElementDto.builder()
                .operationType(OperationType.SUM)
                .firstDecimal(new BigDecimal(firstDecimalStr))
                .secondDecimal(new BigDecimal(secondDecimalStr))
                .result(BigDecimal.valueOf(9))
                .build();
        Mockito.when(mockedCalculatorService.sumOperation(Mockito.any()))
                .thenReturn(expectedOutputElementDto);

        ResponseEntity<OutputElementDto> sumResult = calculatorController.sumOperation(inputDto);
        Mockito.verify(mockedCalculatorService, Mockito.times(1))
                .sumOperation(Mockito.any());
        Assertions.assertEquals(
                ResponseEntity.ok(expectedOutputElementDto),
                sumResult
        );
    }

    @Test
    void testShouldMakeSubtractionOperationWithHttpStatus20O() {
        String firstDecimalStr = "-11";
        String secondDecimalStr = "3";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();
        OutputElementDto expectedOutputElementDto = OutputElementDto.builder()
                .operationType(OperationType.SUBTRACTION)
                .firstDecimal(new BigDecimal(firstDecimalStr))
                .secondDecimal(new BigDecimal(secondDecimalStr))
                .result(BigDecimal.valueOf(-14))
                .build();
        Mockito.when(mockedCalculatorService.subtractionOperation(Mockito.any()))
                .thenReturn(expectedOutputElementDto);

        ResponseEntity<OutputElementDto> subtractionResult = calculatorController.subtractionOperation(inputDto);
        Mockito.verify(mockedCalculatorService, Mockito.times(1))
                .subtractionOperation(Mockito.any());
        Assertions.assertEquals(
                ResponseEntity.ok(expectedOutputElementDto),
                subtractionResult
        );
    }

    @Test
    void testShouldMakeMultiplicationOperationWithHttpStatus20O() {
        String firstDecimalStr = "20";
        String secondDecimalStr = "1";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();
        OutputElementDto expectedOutputElementDto = OutputElementDto.builder()
                .operationType(OperationType.MULTIPLICATION)
                .firstDecimal(new BigDecimal(firstDecimalStr))
                .secondDecimal(new BigDecimal(secondDecimalStr))
                .result(BigDecimal.valueOf(20))
                .build();
        Mockito.when(mockedCalculatorService.multiplicationOperation(Mockito.any()))
                .thenReturn(expectedOutputElementDto);

        ResponseEntity<OutputElementDto> multiplicationResult = calculatorController.multiplicationOperation(inputDto);
        Mockito.verify(mockedCalculatorService, Mockito.times(1))
                .multiplicationOperation(Mockito.any());
        Assertions.assertEquals(
                ResponseEntity.ok(expectedOutputElementDto),
                multiplicationResult
        );
    }

    @Test
    void testShouldMakeDivisionOperationWithHttpStatus20O() {
        String firstDecimalStr = "15";
        String secondDecimalStr = "5";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();
        OutputElementDto expectedOutputElementDto = OutputElementDto.builder()
                .operationType(OperationType.DIVISION)
                .firstDecimal(new BigDecimal(firstDecimalStr))
                .secondDecimal(new BigDecimal(secondDecimalStr))
                .result(BigDecimal.valueOf(3))
                .build();
        Mockito.when(mockedCalculatorService.divisionOperation(Mockito.any()))
                .thenReturn(expectedOutputElementDto);

        ResponseEntity<OutputElementDto> divisionResult = calculatorController.divisionOperation(inputDto);
        Mockito.verify(mockedCalculatorService, Mockito.times(1))
                .divisionOperation(Mockito.any());
        Assertions.assertEquals(
                ResponseEntity.ok(expectedOutputElementDto),
                divisionResult
        );
    }

    @Test
    void testShouldGetOperationsWithHttpStatus20O() {
        String inputId = "1234567890abc";
        String inputOperationType = "SUM";
        DateTime inputCreatedOn = DateTime.parse("2022-10-31T18:36:01.608Z");
        DateTime inputUpdatedOn = DateTime.parse("2022-11-01T06:59:12.380Z");
        Long totalResults = 20L;
        Integer inputPageNumber = 0;
        Integer inputPageSize = 10;
        OutputElementDto expectedOutputElementDto = OutputElementDto.builder()
                .operationType(OperationType.SUM)
                .firstDecimal(new BigDecimal("131.08"))
                .secondDecimal(new BigDecimal("94.73"))
                .result(BigDecimal.valueOf(225.81))
                .build();
        OutputDto expectedOutputDto = OutputDto.builder()
                .operationsList(List.of(expectedOutputElementDto))
                .totalResults(totalResults)
                .pageNumber(inputPageNumber)
                .pageSize(inputPageSize)
                .build();
        Mockito.when(mockedCalculatorService.getOperations(Mockito.anyString(), Mockito.anyString(),
                        Mockito.any(), Mockito.any(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(expectedOutputDto);

        ResponseEntity<OutputDto> divisionResult = calculatorController.getOperations(
                inputId, inputOperationType, inputCreatedOn, inputUpdatedOn, inputPageNumber, inputPageSize
        );
        Mockito.verify(mockedCalculatorService, Mockito.times(1))
                .getOperations(Mockito.anyString(), Mockito.anyString(), Mockito.any(), Mockito.any(),
                        Mockito.anyInt(), Mockito.anyInt());
        Assertions.assertEquals(
                ResponseEntity.ok(expectedOutputDto),
                divisionResult
        );
    }
}
