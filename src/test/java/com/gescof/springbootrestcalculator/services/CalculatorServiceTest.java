package com.gescof.springbootrestcalculator.services;

import com.gescof.springbootrestcalculator.models.InputDto;
import com.gescof.springbootrestcalculator.models.OutputDto;
import com.gescof.springbootrestcalculator.models.OutputElementDto;
import com.gescof.springbootrestcalculator.models.enums.OperationType;
import com.gescof.springbootrestcalculator.persistence.models.EOperation;
import com.gescof.springbootrestcalculator.persistence.repositories.OperationRepository;
import com.querydsl.core.types.Predicate;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Collections;

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
    void testShouldMakeSumOperationReturningOutputElementDto() {
        String firstDecimalStr = "2";
        String secondDecimalStr = "7";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();
        BigDecimal firstDecimal = new BigDecimal(firstDecimalStr);
        BigDecimal secondDecimal = new BigDecimal(secondDecimalStr);
        BigDecimal result = BigDecimal.valueOf(9);
        OutputElementDto expectedOutputElementDto = OutputElementDto.builder()
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

        OutputElementDto sumResult = calculatorService.sumOperation(inputDto);
        Mockito.verify(mockedOperationRepository, Mockito.times(1)).save(Mockito.any());
        Assertions.assertEquals(
                expectedOutputElementDto,
                sumResult
        );
    }

    @Test
    void testShouldNotMakeSumOperationThrowingNumberFormatException() {
        String firstDecimalStr = "invalid_number";
        String secondDecimalStr = "7";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();

        Mockito.verify(mockedOperationRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertThrows(
                NumberFormatException.class, () -> calculatorService.sumOperation(inputDto)
        );
    }

    @Test
    void testShouldMakeSubtractionOperationReturningOutputElementDto() {
        String firstDecimalStr = "-11";
        String secondDecimalStr = "3";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();
        BigDecimal firstDecimal = new BigDecimal(firstDecimalStr);
        BigDecimal secondDecimal = new BigDecimal(secondDecimalStr);
        BigDecimal result = BigDecimal.valueOf(-14);
        OutputElementDto expectedOutputElementDto = OutputElementDto.builder()
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

        OutputElementDto subtractionResult = calculatorService.subtractionOperation(inputDto);
        Mockito.verify(mockedOperationRepository, Mockito.times(1)).save(Mockito.any());
        Assertions.assertEquals(
                expectedOutputElementDto,
                subtractionResult
        );
    }

    @Test
    void testShouldNotMakeSubtractionOperationThrowingNumberFormatException() {
        String firstDecimalStr = "-11";
        String secondDecimalStr = "invalid_number";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();

        Mockito.verify(mockedOperationRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertThrows(
                NumberFormatException.class, () -> calculatorService.subtractionOperation(inputDto)
        );
    }

    @Test
    void testShouldMakeMultiplicationOperationReturningOutputElementDto() {
        String firstDecimalStr = "20";
        String secondDecimalStr = "1";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();
        BigDecimal firstDecimal = new BigDecimal(firstDecimalStr);
        BigDecimal secondDecimal = new BigDecimal(secondDecimalStr);
        BigDecimal result = BigDecimal.valueOf(20);
        OutputElementDto expectedOutputElementDto = OutputElementDto.builder()
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

        OutputElementDto multiplicationResult = calculatorService.multiplicationOperation(inputDto);
        Mockito.verify(mockedOperationRepository, Mockito.times(1)).save(Mockito.any());
        Assertions.assertEquals(
                expectedOutputElementDto,
                multiplicationResult
        );
    }

    @Test
    void testShouldNotMakeMultiplicationOperationThrowingNumberFormatException() {
        String firstDecimalStr = "invalid_number";
        String secondDecimalStr = "invalid_number";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();

        Mockito.verify(mockedOperationRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertThrows(
                NumberFormatException.class, () -> calculatorService.multiplicationOperation(inputDto)
        );
    }

    @Test
    void testShouldMakeDivisionOperationReturningOutputElementDto() {
        String firstDecimalStr = "15";
        String secondDecimalStr = "5";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();
        BigDecimal firstDecimal = new BigDecimal(firstDecimalStr);
        BigDecimal secondDecimal = new BigDecimal(secondDecimalStr);
        BigDecimal result = BigDecimal.valueOf(3);
        OutputElementDto expectedOutputElementDto = OutputElementDto.builder()
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

        OutputElementDto divisionResult = calculatorService.divisionOperation(inputDto);
        Mockito.verify(mockedOperationRepository, Mockito.times(1)).save(Mockito.any());
        Assertions.assertEquals(
                expectedOutputElementDto,
                divisionResult
        );
    }

    @Test
    void testShouldNotMakeDivisionOperationThrowingNumberFormatException() {
        String firstDecimalStr = "";
        String secondDecimalStr = " ";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();

        Mockito.verify(mockedOperationRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertThrows(
                NumberFormatException.class, () -> calculatorService.divisionOperation(inputDto)
        );
    }

    @Test
    void testShouldNotMakeDivisionOperationThrowingArithmeticException() {
        String firstDecimalStr = "9";
        String secondDecimalStr = "0";
        InputDto inputDto = InputDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();

        Mockito.verify(mockedOperationRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertThrows(
                ArithmeticException.class, () -> calculatorService.divisionOperation(inputDto)
        );
    }

    @Test
    void testShouldGetOperationsReturningOutputDto() {
        String inputId = "1234567890abc";
        String inputOperationType = "MULTIPLICATION";
        DateTime inputCreatedOn = DateTime.parse("2022-10-31T18:36:01.608Z");
        DateTime inputUpdatedOn = DateTime.parse("2022-11-01T06:59:12.380Z");
        Long totalResults = 0L;
        Integer inputPageNumber = 1;
        Integer inputPageSize = 5;
        Page<EOperation> expectedQueriedResult = Page.empty();
        OutputDto expectedOutputDto = OutputDto.builder()
                .operationsList(Collections.emptyList())
                .totalResults(totalResults)
                .pageNumber(inputPageNumber)
                .pageSize(inputPageSize)
                .build();
        Mockito.when(mockedOperationRepository.findAll((Predicate) Mockito.any(), (Pageable) Mockito.any()))
                .thenReturn(expectedQueriedResult);

        OutputDto getResult = calculatorService.getOperations(inputId, inputOperationType, inputCreatedOn, inputUpdatedOn,
                inputPageNumber, inputPageSize);
        Mockito.verify(mockedOperationRepository, Mockito.times(1))
                .findAll((Predicate) Mockito.any(), (Pageable) Mockito.any());
        Assertions.assertEquals(
                getResult,
                expectedOutputDto
        );
    }
}
