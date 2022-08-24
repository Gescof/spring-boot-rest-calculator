package com.gescof.springbootrestcalculator.services;

import com.gescof.springbootrestcalculator.model.ResultDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Log4j2
@Service
public class CalculatorService {
    /**
     * Gets the sum between two decimal values.
     *
     * @param firstDecimal  the first decimal from input (first addend)
     * @param secondDecimal the second decimal from input (second addend)
     * @return the sum as a {@link ResultDTO} object
     */
    public ResultDTO getSumResult(
            final String firstDecimal,
            final String secondDecimal
    ) {
        log.debug("Starting get sum result");
        BigDecimal firstAddend = new BigDecimal(firstDecimal);
        BigDecimal secondAddend = new BigDecimal(secondDecimal);
        return ResultDTO.builder()
                .firstDecimal(firstAddend)
                .secondDecimal(secondAddend)
                .result(firstAddend.add(secondAddend))
                .build();
    }

    /**
     * Gets the subtraction between two decimal values.
     *
     * @param firstDecimal  the first decimal from input (minuend)
     * @param secondDecimal the second decimal from input (subtrahend)
     * @return the subtraction as a {@link ResultDTO} object
     */
    public ResultDTO getSubtractionResult(
            final String firstDecimal,
            final String secondDecimal
    ) {
        log.debug("Starting get subtraction result");
        BigDecimal minuend = new BigDecimal(firstDecimal);
        BigDecimal subtrahend = new BigDecimal(secondDecimal);
        return ResultDTO.builder()
                .firstDecimal(minuend)
                .secondDecimal(subtrahend)
                .result(minuend.subtract(subtrahend))
                .build();
    }

    /**
     * Gets the multiplication between two decimal values.
     *
     * @param firstDecimal  the first decimal from input (multiplier)
     * @param secondDecimal the second decimal from input (multiplicand)
     * @return the multiplication as a {@link ResultDTO} object
     */
    public ResultDTO getMultiplicationResult(
            final String firstDecimal,
            final String secondDecimal
    ) {
        log.debug("Starting get multiplication result");
        BigDecimal multiplier = new BigDecimal(firstDecimal);
        BigDecimal multiplicand = new BigDecimal(secondDecimal);
        return ResultDTO.builder()
                .firstDecimal(multiplier)
                .secondDecimal(multiplicand)
                .result(multiplier.multiply(multiplicand))
                .build();
    }

    /**
     * Gets the division between two decimal values.
     *
     * @param firstDecimal  the first decimal from input (dividend)
     * @param secondDecimal the second decimal from input (divisor)
     * @return the division as a {@link ResultDTO} object
     */
    public ResultDTO getDivisionResult(
            final String firstDecimal,
            final String secondDecimal
    ) {
        log.debug("Starting get division result");
        BigDecimal dividend = new BigDecimal(firstDecimal);
        BigDecimal divisor = new BigDecimal(secondDecimal);
        return ResultDTO.builder()
                .firstDecimal(dividend)
                .secondDecimal(divisor)
                .result(dividend.divide(divisor, RoundingMode.CEILING))
                .build();
    }
}
