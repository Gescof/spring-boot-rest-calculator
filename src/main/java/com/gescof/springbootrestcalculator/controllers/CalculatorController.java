package com.gescof.springbootrestcalculator.controllers;

import com.gescof.springbootrestcalculator.models.InputOperationDto;
import com.gescof.springbootrestcalculator.models.OutputOperationDto;
import com.gescof.springbootrestcalculator.models.OutputOperationElementDto;
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
    public ResponseEntity<OutputOperationElementDto> sumOperation(
            @RequestBody final InputOperationDto inputOperationDto
    ) {
        return ResponseEntity.ok(calculatorService.sumOperation(inputOperationDto));
    }

    @PostMapping(value = "${com.gescof.calculator.api.subtractionPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputOperationElementDto> subtractionOperation(
            @RequestBody final InputOperationDto inputOperationDto
    ) {
        return ResponseEntity.ok(calculatorService.subtractionOperation(inputOperationDto));
    }

    @PostMapping(value = "${com.gescof.calculator.api.multiplicationPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputOperationElementDto> multiplicationOperation(
            @RequestBody final InputOperationDto inputOperationDto
    ) {
        return ResponseEntity.ok(calculatorService.multiplicationOperation(inputOperationDto));
    }

    @PostMapping(value = "${com.gescof.calculator.api.divisionPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputOperationElementDto> divisionOperation(
            @RequestBody final InputOperationDto inputOperationDto
    ) {
        return ResponseEntity.ok(calculatorService.divisionOperation(inputOperationDto));
    }

    @GetMapping(value = "${com.gescof.calculator.api.operationsPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputOperationDto> getOperations(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String operationType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime createdOnFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime createdOnTo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime updatedOnFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime updatedOnTo,
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize
    ) {
        return ResponseEntity.ok(calculatorService.getOperations(
                id, operationType,
                createdOnFrom, createdOnTo,
                updatedOnFrom, updatedOnTo,
                pageNumber, pageSize
        ));
    }
}
