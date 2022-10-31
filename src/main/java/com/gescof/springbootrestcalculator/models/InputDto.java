package com.gescof.springbootrestcalculator.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InputDto {
    private String firstDecimal;
    private String secondDecimal;
}
