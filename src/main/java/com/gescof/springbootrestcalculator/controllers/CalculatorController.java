package com.gescof.springbootrestcalculator.controllers;

import com.gescof.springbootrestcalculator.model.ResultDTO;
import com.gescof.springbootrestcalculator.services.CalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${com.gescof.calculator.api.basePath}")
public class CalculatorController {
    private final CalculatorService calculatorService;

    @GetMapping(value = "${com.gescof.calculator.api.sumPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDTO> getSumResult(
            @RequestParam final String firstDecimal,
            @RequestParam final String secondDecimal
    ) {
        return ResponseEntity.ok(calculatorService.getSumResult(firstDecimal, secondDecimal));
    }

    @GetMapping(value = "${com.gescof.calculator.api.subtractionPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDTO> getSubtractionResult(
            @RequestParam final String firstDecimal,
            @RequestParam final String secondDecimal
    ) {
        return ResponseEntity.ok(calculatorService.getSubtractionResult(firstDecimal, secondDecimal));
    }

    @GetMapping(value = "${com.gescof.calculator.api.multiplicationPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDTO> getMultiplicationResult(
            @RequestParam final String firstDecimal,
            @RequestParam final String secondDecimal
    ) {
        return ResponseEntity.ok(calculatorService.getMultiplicationResult(firstDecimal, secondDecimal));
    }

    @GetMapping(value = "${com.gescof.calculator.api.divisionPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDTO> getDivisionResult(
            @RequestParam final String firstDecimal,
            @RequestParam final String secondDecimal
    ) {
        return ResponseEntity.ok(calculatorService.getDivisionResult(firstDecimal, secondDecimal));
    }
}
