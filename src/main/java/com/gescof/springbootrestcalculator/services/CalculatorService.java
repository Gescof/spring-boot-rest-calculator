package com.gescof.springbootrestcalculator.services;

import com.gescof.springbootrestcalculator.models.InputOperationDto;
import com.gescof.springbootrestcalculator.models.OutputOperationDto;
import com.gescof.springbootrestcalculator.models.OutputOperationElementDto;
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
     * @param inputOperationDto object containing the first decimal from input (first addend)
     *                          and the second decimal from input (second addend)
     * @return the sum as a {@link OutputOperationElementDto} object
     */
    public OutputOperationElementDto sumOperation(
            final InputOperationDto inputOperationDto
    ) {
        log.debug("Starting POST sum operation");
        var firstAddend = new BigDecimal(inputOperationDto.getFirstDecimal());
        var secondAddend = new BigDecimal(inputOperationDto.getSecondDecimal());
        var outputDto = OutputOperationElementDto.builder()
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
     * @param inputOperationDto object containing the first decimal from input (minuend)
     *                          and the second decimal from input (subtrahend)
     * @return the subtraction as a {@link OutputOperationElementDto} object
     */
    public OutputOperationElementDto subtractionOperation(
            final InputOperationDto inputOperationDto
    ) {
        log.debug("Starting POST subtraction operation");
        var minuend = new BigDecimal(inputOperationDto.getFirstDecimal());
        var subtrahend = new BigDecimal(inputOperationDto.getSecondDecimal());
        var outputDto = OutputOperationElementDto.builder()
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
     * @param inputOperationDto object containing the first decimal from input (multiplier)
     *                          and the second decimal from input (multiplicand)
     * @return the multiplication as a {@link OutputOperationElementDto} object
     */
    public OutputOperationElementDto multiplicationOperation(
            final InputOperationDto inputOperationDto
    ) {
        log.debug("Starting POST multiplication operation");
        var multiplier = new BigDecimal(inputOperationDto.getFirstDecimal());
        var multiplicand = new BigDecimal(inputOperationDto.getSecondDecimal());
        var outputDto = OutputOperationElementDto.builder()
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
     * @param inputOperationDto object containing  the first decimal from input (dividend)
     *                          and the second decimal from input (divisor)
     * @return the division as a {@link OutputOperationElementDto} object
     */
    public OutputOperationElementDto divisionOperation(
            final InputOperationDto inputOperationDto
    ) {
        log.debug("Starting POST division operation");
        var dividend = new BigDecimal(inputOperationDto.getFirstDecimal());
        var divisor = new BigDecimal(inputOperationDto.getSecondDecimal());
        var outputDto = OutputOperationElementDto.builder()
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
     * @param createdOnFrom the date to query from of creation for the operation if present in request.
     *                      Must match date pattern of {@link DateTime}
     * @param createdOnTo   the date to query to of creation for the operation if present in request.
     *                      Must match date pattern of {@link DateTime}
     * @param updatedOnFrom the date to query from of last modification for the operation if present in request.
     *                      Must match date pattern of {@link DateTime}
     * @param updatedOnTo   the date to query to of last modification for the operation if present in request.
     *                      Must match date pattern of {@link DateTime}
     * @param pageNumber    the page number to query
     * @param pageSize      the page size to query
     * @return all the operations as a {@link List} containing {@link OutputOperationElementDto} objects
     */
    @Cacheable("operations")
    public OutputOperationDto getOperations(
            final String id,
            final String operationType,
            final DateTime createdOnFrom,
            final DateTime createdOnTo,
            final DateTime updatedOnFrom,
            final DateTime updatedOnTo,
            final Integer pageNumber,
            final Integer pageSize
    ) {
        log.debug("Starting GET all operations");
        var qEOperation = new QEOperation("operation");
        var predicate = getPredicateQueryFilter(
                qEOperation, id, operationType, createdOnFrom, createdOnTo, updatedOnFrom, updatedOnTo);
        var pageable = PageRequest.of(pageNumber, pageSize);
        var operationsList = new ArrayList<OutputOperationElementDto>();
        var result = operationRepository.findAll(predicate, pageable);
        result.forEach(eOperation -> operationsList.add(OperationMapper.mapEOperationToOutputDto(eOperation)));
        return OutputOperationDto.builder()
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
            final DateTime createdOnFrom,
            final DateTime createdOnTo,
            final DateTime updatedOnFrom,
            final DateTime updatedOnTo
    ) {
        var predicate = new BooleanBuilder();
        if (Objects.nonNull(id)) {
            predicate.and(qEOperation.id.eq(id));
        }
        if (Objects.nonNull(operationType)) {
            predicate.and(qEOperation.operationType.eq(OperationType.valueOf(operationType)));
        }
        if (Objects.nonNull(createdOnFrom)) {
            predicate.and(qEOperation.createdOn.after(createdOnFrom));
        }
        if (Objects.nonNull(createdOnTo)) {
            predicate.and(qEOperation.createdOn.before(createdOnTo));
        }
        if (Objects.nonNull(updatedOnFrom)) {
            predicate.and(qEOperation.updatedOn.after(updatedOnFrom));
        }
        if (Objects.nonNull(updatedOnTo)) {
            predicate.and(qEOperation.updatedOn.before(updatedOnTo));
        }
        return predicate;
    }
}
