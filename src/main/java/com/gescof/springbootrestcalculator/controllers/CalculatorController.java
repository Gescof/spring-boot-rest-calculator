package com.gescof.springbootrestcalculator.controllers;

import com.gescof.springbootrestcalculator.models.InputDto;
import com.gescof.springbootrestcalculator.models.OutputDto;
import com.gescof.springbootrestcalculator.services.CalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${com.gescof.calculator.api.basePath}")
public class CalculatorController {
    private final CalculatorService calculatorService;

    @PostMapping(value = "${com.gescof.calculator.api.sumPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputDto> getSumResult(
            @RequestBody final InputDto inputDto
    ) {
        return ResponseEntity.ok(calculatorService.getSumResult(inputDto));
    }

    @PostMapping(value = "${com.gescof.calculator.api.subtractionPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputDto> getSubtractionResult(
            @RequestBody final InputDto inputDto
    ) {
        return ResponseEntity.ok(calculatorService.getSubtractionResult(inputDto));
    }

    @PostMapping(value = "${com.gescof.calculator.api.multiplicationPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputDto> getMultiplicationResult(
            @RequestBody final InputDto inputDto
    ) {
        return ResponseEntity.ok(calculatorService.getMultiplicationResult(inputDto));
    }

    @PostMapping(value = "${com.gescof.calculator.api.divisionPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputDto> getDivisionResult(
            @RequestBody final InputDto inputDto
    ) {
        return ResponseEntity.ok(calculatorService.getDivisionResult(inputDto));
    }
}
