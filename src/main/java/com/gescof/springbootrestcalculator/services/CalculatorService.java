package com.gescof.springbootrestcalculator.services;

import com.gescof.springbootrestcalculator.models.InputDto;
import com.gescof.springbootrestcalculator.models.OutputDto;
import com.gescof.springbootrestcalculator.models.enums.OperationType;
import com.gescof.springbootrestcalculator.persistence.repositories.OperationRepository;
import com.gescof.springbootrestcalculator.utils.OperationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
@Log4j2
@Service
public class CalculatorService {
    private final OperationRepository operationRepository;

    /**
     * Gets the sum between two decimal values.
     *
     * @param inputDto object containing the first decimal from input (first addend)
     *                 and the second decimal from input (second addend)
     * @return the sum as a {@link OutputDto} object
     */
    public OutputDto getSumResult(
            final InputDto inputDto
    ) {
        log.debug("Starting get sum result");
        BigDecimal firstAddend = new BigDecimal(inputDto.getFirstDecimal());
        BigDecimal secondAddend = new BigDecimal(inputDto.getSecondDecimal());
        var outputDto = OutputDto.builder()
                .operationType(OperationType.SUM)
                .firstDecimal(firstAddend)
                .secondDecimal(secondAddend)
                .result(firstAddend.add(secondAddend))
                .build();
        var operationEntity = operationRepository.save(OperationMapper.mapOutputDtoToEOperation(outputDto));
        return OperationMapper.mapEOperationToOutputDto(operationEntity);
    }

    /**
     * Gets the subtraction between two decimal values.
     *
     * @param inputDto object containing the first decimal from input (minuend)
     *                 and the second decimal from input (subtrahend)
     * @return the subtraction as a {@link OutputDto} object
     */
    public OutputDto getSubtractionResult(
            final InputDto inputDto
    ) {
        log.debug("Starting get subtraction result");
        BigDecimal minuend = new BigDecimal(inputDto.getFirstDecimal());
        BigDecimal subtrahend = new BigDecimal(inputDto.getSecondDecimal());
        var outputDto = OutputDto.builder()
                .operationType(OperationType.SUBTRACTION)
                .firstDecimal(minuend)
                .secondDecimal(subtrahend)
                .result(minuend.subtract(subtrahend))
                .build();
        var operationEntity = operationRepository.save(OperationMapper.mapOutputDtoToEOperation(outputDto));
        return OperationMapper.mapEOperationToOutputDto(operationEntity);
    }

    /**
     * Gets the multiplication between two decimal values.
     *
     * @param inputDto object containing the first decimal from input (multiplier)
     *                 and the second decimal from input (multiplicand)
     * @return the multiplication as a {@link OutputDto} object
     */
    public OutputDto getMultiplicationResult(
            final InputDto inputDto
    ) {
        log.debug("Starting get multiplication result");
        BigDecimal multiplier = new BigDecimal(inputDto.getFirstDecimal());
        BigDecimal multiplicand = new BigDecimal(inputDto.getSecondDecimal());
        var outputDto = OutputDto.builder()
                .operationType(OperationType.MULTIPLICATION)
                .firstDecimal(multiplier)
                .secondDecimal(multiplicand)
                .result(multiplier.multiply(multiplicand))
                .build();
        var operationEntity = operationRepository.save(OperationMapper.mapOutputDtoToEOperation(outputDto));
        return OperationMapper.mapEOperationToOutputDto(operationEntity);
    }

    /**
     * Gets the division between two decimal values.
     *
     * @param inputDto object containing  the first decimal from input (dividend)
     *                 and the second decimal from input (divisor)
     * @return the division as a {@link OutputDto} object
     */
    public OutputDto getDivisionResult(
            final InputDto inputDto
    ) {
        log.debug("Starting get division result");
        BigDecimal dividend = new BigDecimal(inputDto.getFirstDecimal());
        BigDecimal divisor = new BigDecimal(inputDto.getSecondDecimal());
        var outputDto = OutputDto.builder()
                .operationType(OperationType.DIVISION)
                .firstDecimal(dividend)
                .secondDecimal(divisor)
                .result(dividend.divide(divisor, RoundingMode.CEILING))
                .build();
        var operationEntity = operationRepository.save(OperationMapper.mapOutputDtoToEOperation(outputDto));
        return OperationMapper.mapEOperationToOutputDto(operationEntity);
    }
}
