package com.gescof.springbootrestcalculator.models.inputs;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InputOperationDto {
    private String firstDecimal;
    private String secondDecimal;
}
