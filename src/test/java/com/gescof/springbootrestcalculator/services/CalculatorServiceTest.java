package com.gescof.springbootrestcalculator.services;

import com.gescof.springbootrestcalculator.models.InputOperationDto;
import com.gescof.springbootrestcalculator.models.OutputOperationDto;
import com.gescof.springbootrestcalculator.models.OutputOperationElementDto;
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
        InputOperationDto inputOperationDto = InputOperationDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();
        BigDecimal firstDecimal = new BigDecimal(firstDecimalStr);
        BigDecimal secondDecimal = new BigDecimal(secondDecimalStr);
        BigDecimal result = BigDecimal.valueOf(9);
        OutputOperationElementDto expectedOutputOperationElementDto = OutputOperationElementDto.builder()
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

        OutputOperationElementDto sumResult = calculatorService.sumOperation(inputOperationDto);
        Mockito.verify(mockedOperationRepository, Mockito.times(1)).save(Mockito.any());
        Assertions.assertEquals(
                expectedOutputOperationElementDto,
                sumResult
        );
    }

    @Test
    void testShouldNotMakeSumOperationThrowingNumberFormatException() {
        String firstDecimalStr = "invalid_number";
        String secondDecimalStr = "7";
        InputOperationDto inputOperationDto = InputOperationDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();

        Mockito.verify(mockedOperationRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertThrows(
                NumberFormatException.class, () -> calculatorService.sumOperation(inputOperationDto)
        );
    }

    @Test
    void testShouldMakeSubtractionOperationReturningOutputElementDto() {
        String firstDecimalStr = "-11";
        String secondDecimalStr = "3";
        InputOperationDto inputOperationDto = InputOperationDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();
        BigDecimal firstDecimal = new BigDecimal(firstDecimalStr);
        BigDecimal secondDecimal = new BigDecimal(secondDecimalStr);
        BigDecimal result = BigDecimal.valueOf(-14);
        OutputOperationElementDto expectedOutputOperationElementDto = OutputOperationElementDto.builder()
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

        OutputOperationElementDto subtractionResult = calculatorService.subtractionOperation(inputOperationDto);
        Mockito.verify(mockedOperationRepository, Mockito.times(1)).save(Mockito.any());
        Assertions.assertEquals(
                expectedOutputOperationElementDto,
                subtractionResult
        );
    }

    @Test
    void testShouldNotMakeSubtractionOperationThrowingNumberFormatException() {
        String firstDecimalStr = "-11";
        String secondDecimalStr = "invalid_number";
        InputOperationDto inputOperationDto = InputOperationDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();

        Mockito.verify(mockedOperationRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertThrows(
                NumberFormatException.class, () -> calculatorService.subtractionOperation(inputOperationDto)
        );
    }

    @Test
    void testShouldMakeMultiplicationOperationReturningOutputElementDto() {
        String firstDecimalStr = "20";
        String secondDecimalStr = "1";
        InputOperationDto inputOperationDto = InputOperationDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();
        BigDecimal firstDecimal = new BigDecimal(firstDecimalStr);
        BigDecimal secondDecimal = new BigDecimal(secondDecimalStr);
        BigDecimal result = BigDecimal.valueOf(20);
        OutputOperationElementDto expectedOutputOperationElementDto = OutputOperationElementDto.builder()
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

        OutputOperationElementDto multiplicationResult = calculatorService.multiplicationOperation(inputOperationDto);
        Mockito.verify(mockedOperationRepository, Mockito.times(1)).save(Mockito.any());
        Assertions.assertEquals(
                expectedOutputOperationElementDto,
                multiplicationResult
        );
    }

    @Test
    void testShouldNotMakeMultiplicationOperationThrowingNumberFormatException() {
        String firstDecimalStr = "invalid_number";
        String secondDecimalStr = "invalid_number";
        InputOperationDto inputOperationDto = InputOperationDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();

        Mockito.verify(mockedOperationRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertThrows(
                NumberFormatException.class, () -> calculatorService.multiplicationOperation(inputOperationDto)
        );
    }

    @Test
    void testShouldMakeDivisionOperationReturningOutputElementDto() {
        String firstDecimalStr = "15";
        String secondDecimalStr = "5";
        InputOperationDto inputOperationDto = InputOperationDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();
        BigDecimal firstDecimal = new BigDecimal(firstDecimalStr);
        BigDecimal secondDecimal = new BigDecimal(secondDecimalStr);
        BigDecimal result = BigDecimal.valueOf(3);
        OutputOperationElementDto expectedOutputOperationElementDto = OutputOperationElementDto.builder()
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

        OutputOperationElementDto divisionResult = calculatorService.divisionOperation(inputOperationDto);
        Mockito.verify(mockedOperationRepository, Mockito.times(1)).save(Mockito.any());
        Assertions.assertEquals(
                expectedOutputOperationElementDto,
                divisionResult
        );
    }

    @Test
    void testShouldNotMakeDivisionOperationThrowingNumberFormatException() {
        String firstDecimalStr = "";
        String secondDecimalStr = " ";
        InputOperationDto inputOperationDto = InputOperationDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();

        Mockito.verify(mockedOperationRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertThrows(
                NumberFormatException.class, () -> calculatorService.divisionOperation(inputOperationDto)
        );
    }

    @Test
    void testShouldNotMakeDivisionOperationThrowingArithmeticException() {
        String firstDecimalStr = "9";
        String secondDecimalStr = "0";
        InputOperationDto inputOperationDto = InputOperationDto.builder()
                .firstDecimal(firstDecimalStr)
                .secondDecimal(secondDecimalStr)
                .build();

        Mockito.verify(mockedOperationRepository, Mockito.times(0)).save(Mockito.any());
        Assertions.assertThrows(
                ArithmeticException.class, () -> calculatorService.divisionOperation(inputOperationDto)
        );
    }

    @Test
    void testShouldGetOperationsReturningOutputDto() {
        String inputId = "1234567890abc";
        String inputOperationType = "MULTIPLICATION";
        DateTime inputCreatedOnFrom = DateTime.parse("2022-10-31T18:36:01.608Z");
        DateTime inputCreatedOnTo = DateTime.parse("2022-10-31T18:36:01.608Z");
        DateTime inputUpdatedOnFrom = DateTime.parse("2022-11-01T06:59:12.380Z");
        DateTime inputUpdatedOnTo = DateTime.parse("2022-11-01T06:59:12.380Z");
        Long totalResults = 0L;
        Integer inputPageNumber = 1;
        Integer inputPageSize = 5;
        Page<EOperation> expectedQueriedResult = Page.empty();
        OutputOperationDto expectedOutputOperationDto = OutputOperationDto.builder()
                .operationsList(Collections.emptyList())
                .totalResults(totalResults)
                .pageNumber(inputPageNumber)
                .pageSize(inputPageSize)
                .build();
        Mockito.when(mockedOperationRepository.findAll((Predicate) Mockito.any(), (Pageable) Mockito.any()))
                .thenReturn(expectedQueriedResult);

        OutputOperationDto getResult = calculatorService.getOperations(inputId, inputOperationType,
                inputCreatedOnFrom, inputCreatedOnTo, inputUpdatedOnFrom, inputUpdatedOnTo,
                inputPageNumber, inputPageSize);
        Mockito.verify(mockedOperationRepository, Mockito.times(1))
                .findAll((Predicate) Mockito.any(), (Pageable) Mockito.any());
        Assertions.assertEquals(
                getResult,
                expectedOutputOperationDto
        );
    }
}
