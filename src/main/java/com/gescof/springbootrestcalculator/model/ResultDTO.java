package com.gescof.springbootrestcalculator.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ResultDTO {
    private BigDecimal firstDecimal;
    private BigDecimal secondDecimal;
    private BigDecimal result;
}
