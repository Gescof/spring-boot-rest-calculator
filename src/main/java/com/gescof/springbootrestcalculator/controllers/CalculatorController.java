package com.gescof.springbootrestcalculator.controllers;

import com.gescof.springbootrestcalculator.models.InputDto;
import com.gescof.springbootrestcalculator.models.OutputDto;
import com.gescof.springbootrestcalculator.models.OutputElementDto;
import com.gescof.springbootrestcalculator.services.CalculatorService;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${com.gescof.calculator.api.basePath}")
public class CalculatorController {
    private final CalculatorService calculatorService;

    @PostMapping(value = "${com.gescof.calculator.api.sumPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputElementDto> sumOperation(
            @RequestBody final InputDto inputDto
    ) {
        return ResponseEntity.ok(calculatorService.sumOperation(inputDto));
    }

    @PostMapping(value = "${com.gescof.calculator.api.subtractionPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputElementDto> subtractionOperation(
            @RequestBody final InputDto inputDto
    ) {
        return ResponseEntity.ok(calculatorService.subtractionOperation(inputDto));
    }

    @PostMapping(value = "${com.gescof.calculator.api.multiplicationPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputElementDto> multiplicationOperation(
            @RequestBody final InputDto inputDto
    ) {
        return ResponseEntity.ok(calculatorService.multiplicationOperation(inputDto));
    }

    @PostMapping(value = "${com.gescof.calculator.api.divisionPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputElementDto> divisionOperation(
            @RequestBody final InputDto inputDto
    ) {
        return ResponseEntity.ok(calculatorService.divisionOperation(inputDto));
    }

    @GetMapping(value = "${com.gescof.calculator.api.operationsPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputDto> getOperations(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String operationType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime createdOn,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime updatedOn,
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize
    ) {
        return ResponseEntity.ok(calculatorService.getOperations(id, operationType, createdOn, updatedOn,
                pageNumber, pageSize));
    }
}
