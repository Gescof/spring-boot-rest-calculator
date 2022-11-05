package com.gescof.springbootrestcalculator.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InputOperationDto {
    private String firstDecimal;
    private String secondDecimal;
}
