package com.gescof.springbootrestcalculator.services;

import com.gescof.springbootrestcalculator.models.InputDto;
import com.gescof.springbootrestcalculator.models.OutputDto;
import com.gescof.springbootrestcalculator.models.enums.OperationType;
import com.gescof.springbootrestcalculator.persistence.models.EOperation;
import com.gescof.springbootrestcalculator.persistence.repositories.OperationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

class CalculatorServiceTest {
    @InjectMocks
    private CalculatorService calculatorService;
    @Mock
    private OperationRepository mockedOperationRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShouldGetSumResultReturningResultDTO() {
        String firstDecimalStr = "2";
        String secondDecimalStr = "7";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();
        BigDecimal firstDecimal = new BigDecimal(firstDecimalStr);
        BigDecimal secondDecimal = new BigDecimal(secondDecimalStr);
        BigDecimal result = BigDecimal.valueOf(9);
        OutputDto expectedOutputDto = OutputDto.builder()
                .operationType(OperationType.SUM)
                .firstDecimal(firstDecimal)
                .secondDecimal(secondDecimal)
                .result(result)
                .build();
        EOperation expectedEOperation = new EOperation();
        expectedEOperation.setOperationType(OperationType.SUM);
        expectedEOperation.setFirstDecimal(firstDecimal);
        expectedEOperation.setSecondDecimal(secondDecimal);
        expectedEOperation.setResult(result);

        Mockito.when(mockedOperationRepository.save(Mockito.any()))
                .thenReturn(expectedEOperation);

        OutputDto sumResult = calculatorService.getSumResult(inputDto);
        Mockito.verify(mockedOperationRepository, Mockito.times(1)).save(Mockito.any());
        Assertions.assertEquals(
                expectedOutputDto,
                sumResult
        );
    }

    @Test
    void testShouldGetSumResultThrowingNumberFormatException() {
        String firstDecimalStr = "invalid_number";
        String secondDecimalStr = "7";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();

        Mockito.verify(mockedOperationRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertThrows(
                NumberFormatException.class, () -> calculatorService.getSumResult(inputDto)
        );
    }

    @Test
    void testShouldGetSubtractionResultReturningResultDTO() {
        String firstDecimalStr = "-11";
        String secondDecimalStr = "3";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();
        BigDecimal firstDecimal = new BigDecimal(firstDecimalStr);
        BigDecimal secondDecimal = new BigDecimal(secondDecimalStr);
        BigDecimal result = BigDecimal.valueOf(-14);
        OutputDto expectedOutputDto = OutputDto.builder()
                .operationType(OperationType.SUBTRACTION)
                .firstDecimal(firstDecimal)
                .secondDecimal(secondDecimal)
                .result(result)
                .build();
        EOperation expectedEOperation = new EOperation();
        expectedEOperation.setOperationType(OperationType.SUBTRACTION);
        expectedEOperation.setFirstDecimal(firstDecimal);
        expectedEOperation.setSecondDecimal(secondDecimal);
        expectedEOperation.setResult(result);

        Mockito.when(mockedOperationRepository.save(Mockito.any()))
                .thenReturn(expectedEOperation);

        OutputDto subtractionResult = calculatorService.getSubtractionResult(inputDto);
        Mockito.verify(mockedOperationRepository, Mockito.times(1)).save(Mockito.any());
        Assertions.assertEquals(
                expectedOutputDto,
                subtractionResult
        );
    }

    @Test
    void testShouldGetSubtractionResultThrowingNumberFormatException() {
        String firstDecimalStr = "-11";
        String secondDecimalStr = "invalid_number";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();

        Mockito.verify(mockedOperationRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertThrows(
                NumberFormatException.class, () -> calculatorService.getSubtractionResult(inputDto)
        );
    }

    @Test
    void testShouldGetMultiplicationResultReturningResultDTO() {
        String firstDecimalStr = "20";
        String secondDecimalStr = "1";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();
        BigDecimal firstDecimal = new BigDecimal(firstDecimalStr);
        BigDecimal secondDecimal = new BigDecimal(secondDecimalStr);
        BigDecimal result = BigDecimal.valueOf(20);
        OutputDto expectedOutputDto = OutputDto.builder()
                .operationType(OperationType.MULTIPLICATION)
                .firstDecimal(firstDecimal)
                .secondDecimal(secondDecimal)
                .result(result)
                .build();
        EOperation expectedEOperation = new EOperation();
        expectedEOperation.setOperationType(OperationType.MULTIPLICATION);
        expectedEOperation.setFirstDecimal(firstDecimal);
        expectedEOperation.setSecondDecimal(secondDecimal);
        expectedEOperation.setResult(result);

        Mockito.when(mockedOperationRepository.save(Mockito.any()))
                .thenReturn(expectedEOperation);

        OutputDto multiplicationResult = calculatorService.getMultiplicationResult(inputDto);
        Mockito.verify(mockedOperationRepository, Mockito.times(1)).save(Mockito.any());
        Assertions.assertEquals(
                expectedOutputDto,
                multiplicationResult
        );
    }

    @Test
    void testShouldGetMultiplicationResultThrowingNumberFormatException() {
        String firstDecimalStr = "invalid_number";
        String secondDecimalStr = "invalid_number";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();

        Mockito.verify(mockedOperationRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertThrows(
                NumberFormatException.class, () -> calculatorService.getMultiplicationResult(inputDto)
        );
    }

    @Test
    void testShouldGetDivisionResultReturningResultDTO() {
        String firstDecimalStr = "15";
        String secondDecimalStr = "5";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();
        BigDecimal firstDecimal = new BigDecimal(firstDecimalStr);
        BigDecimal secondDecimal = new BigDecimal(secondDecimalStr);
        BigDecimal result = BigDecimal.valueOf(3);
        OutputDto expectedOutputDto = OutputDto.builder()
                .operationType(OperationType.DIVISION)
                .firstDecimal(firstDecimal)
                .secondDecimal(secondDecimal)
                .result(result)
                .build();
        EOperation expectedEOperation = new EOperation();
        expectedEOperation.setOperationType(OperationType.DIVISION);
        expectedEOperation.setFirstDecimal(firstDecimal);
        expectedEOperation.setSecondDecimal(secondDecimal);
        expectedEOperation.setResult(result);

        Mockito.when(mockedOperationRepository.save(Mockito.any()))
                .thenReturn(expectedEOperation);

        OutputDto divisionResult = calculatorService.getDivisionResult(inputDto);
        Mockito.verify(mockedOperationRepository, Mockito.times(1)).save(Mockito.any());
        Assertions.assertEquals(
                expectedOutputDto,
                divisionResult
        );
    }

    @Test
    void testShouldGetDivisionResultThrowingNumberFormatException() {
        String firstDecimal = "";
        String secondDecimal = " ";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimal)
                .secondDecimal(secondDecimal)
                .build();

        Mockito.verify(mockedOperationRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertThrows(
                NumberFormatException.class, () -> calculatorService.getDivisionResult(inputDto)
        );
    }

    @Test
    void testShouldGetDivisionResultThrowingArithmeticException() {
        String firstDecimal = "9";
        String secondDecimal = "0";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimal)
                .secondDecimal(secondDecimal)
                .build();

        Mockito.verify(mockedOperationRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertThrows(
                ArithmeticException.class, () -> calculatorService.getDivisionResult(inputDto)
        );
    }
}
