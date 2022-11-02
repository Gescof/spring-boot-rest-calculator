package com.gescof.springbootrestcalculator.services;

import com.gescof.springbootrestcalculator.models.InputDto;
import com.gescof.springbootrestcalculator.models.OutputDto;
import com.gescof.springbootrestcalculator.models.OutputElementDto;
import com.gescof.springbootrestcalculator.models.enums.OperationType;
import com.gescof.springbootrestcalculator.persistence.models.QEOperation;
import com.gescof.springbootrestcalculator.persistence.repositories.OperationRepository;
import com.gescof.springbootrestcalculator.utils.OperationMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.joda.time.DateTime;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Log4j2
@Service
public class CalculatorService {
    private final OperationRepository operationRepository;

    /**
     * Creates a new operation with the sum between two decimal values.
     *
     * @param inputDto object containing the first decimal from input (first addend)
     *                 and the second decimal from input (second addend)
     * @return the sum as a {@link OutputElementDto} object
     */
    public OutputElementDto sumOperation(
            final InputDto inputDto
    ) {
        log.debug("Starting POST sum operation");
        var firstAddend = new BigDecimal(inputDto.getFirstDecimal());
        var secondAddend = new BigDecimal(inputDto.getSecondDecimal());
        var outputDto = OutputElementDto.builder()
                .operationType(OperationType.SUM)
                .firstDecimal(firstAddend)
                .secondDecimal(secondAddend)
                .result(firstAddend.add(secondAddend))
                .build();
        var operationEntity = operationRepository.save(OperationMapper.mapOutputDtoToEOperation(outputDto));
        return OperationMapper.mapEOperationToOutputDto(operationEntity);
    }

    /**
     * Creates a new operation with the subtraction between two decimal values.
     *
     * @param inputDto object containing the first decimal from input (minuend)
     *                 and the second decimal from input (subtrahend)
     * @return the subtraction as a {@link OutputElementDto} object
     */
    public OutputElementDto subtractionOperation(
            final InputDto inputDto
    ) {
        log.debug("Starting POST subtraction operation");
        var minuend = new BigDecimal(inputDto.getFirstDecimal());
        var subtrahend = new BigDecimal(inputDto.getSecondDecimal());
        var outputDto = OutputElementDto.builder()
                .operationType(OperationType.SUBTRACTION)
                .firstDecimal(minuend)
                .secondDecimal(subtrahend)
                .result(minuend.subtract(subtrahend))
                .build();
        var operationEntity = operationRepository.save(OperationMapper.mapOutputDtoToEOperation(outputDto));
        return OperationMapper.mapEOperationToOutputDto(operationEntity);
    }

    /**
     * Creates a new operation with the multiplication between two decimal values.
     *
     * @param inputDto object containing the first decimal from input (multiplier)
     *                 and the second decimal from input (multiplicand)
     * @return the multiplication as a {@link OutputElementDto} object
     */
    public OutputElementDto multiplicationOperation(
            final InputDto inputDto
    ) {
        log.debug("Starting POST multiplication operation");
        var multiplier = new BigDecimal(inputDto.getFirstDecimal());
        var multiplicand = new BigDecimal(inputDto.getSecondDecimal());
        var outputDto = OutputElementDto.builder()
                .operationType(OperationType.MULTIPLICATION)
                .firstDecimal(multiplier)
                .secondDecimal(multiplicand)
                .result(multiplier.multiply(multiplicand))
                .build();
        var operationEntity = operationRepository.save(OperationMapper.mapOutputDtoToEOperation(outputDto));
        return OperationMapper.mapEOperationToOutputDto(operationEntity);
    }

    /**
     * Creates a new operation with the division between two decimal values.
     *
     * @param inputDto object containing  the first decimal from input (dividend)
     *                 and the second decimal from input (divisor)
     * @return the division as a {@link OutputElementDto} object
     */
    public OutputElementDto divisionOperation(
            final InputDto inputDto
    ) {
        log.debug("Starting POST division operation");
        var dividend = new BigDecimal(inputDto.getFirstDecimal());
        var divisor = new BigDecimal(inputDto.getSecondDecimal());
        var outputDto = OutputElementDto.builder()
                .operationType(OperationType.DIVISION)
                .firstDecimal(dividend)
                .secondDecimal(divisor)
                .result(dividend.divide(divisor, RoundingMode.CEILING))
                .build();
        var operationEntity = operationRepository.save(OperationMapper.mapOutputDtoToEOperation(outputDto));
        return OperationMapper.mapEOperationToOutputDto(operationEntity);
    }

    /**
     * Gets all the operations stored.
     *
     * @param id            the id to filter if present in request
     * @param operationType the operation type to filter if present in request.
     *                      Must match one of the possible values from enum {@link OperationType}
     * @param createdOn     the date of creation for the operation if present in request.
     *                      Must match date pattern of {@link DateTime}
     * @param updatedOn     the date of last modification for the operation if present in request.
     *                      Must match date pattern of {@link DateTime}
     * @param pageNumber    the page number to query
     * @param pageSize      the page size to query
     * @return all the operations as a {@link List} containing {@link OutputElementDto} objects
     */
    @Cacheable("operations")
    public OutputDto getOperations(
            final String id,
            final String operationType,
            final DateTime createdOn,
            final DateTime updatedOn,
            final Integer pageNumber,
            final Integer pageSize
    ) {
        log.debug("Starting GET all operations");
        var qEOperation = new QEOperation("operation");
        var predicate = getPredicateQueryFilter(qEOperation, id, operationType, createdOn, updatedOn);
        var pageable = PageRequest.of(pageNumber, pageSize);
        var operationsList = new ArrayList<OutputElementDto>();
        var result = operationRepository.findAll(predicate, pageable);
        result.forEach(eOperation -> operationsList.add(OperationMapper.mapEOperationToOutputDto(eOperation)));
        return OutputDto.builder()
                .operationsList(operationsList)
                .totalResults(result.getTotalElements())
                .pageNumber(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .build();
    }

    private Predicate getPredicateQueryFilter(
            final QEOperation qEOperation,
            final String id,
            final String operationType,
            final DateTime createdOn,
            final DateTime updatedOn
    ) {
        var predicate = new BooleanBuilder();
        if (Objects.nonNull(id)) {
            predicate.and(qEOperation.id.eq(id));
        }
        if (Objects.nonNull(operationType)) {
            predicate.and(qEOperation.operationType.eq(OperationType.valueOf(operationType)));
        }
        if (Objects.nonNull(createdOn)) {
            predicate.and(qEOperation.createdOn.eq(createdOn));
        }
        if (Objects.nonNull(updatedOn)) {
            predicate.and(qEOperation.updatedOn.eq(updatedOn));
        }
        return predicate;
    }
}
