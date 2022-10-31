package com.gescof.springbootrestcalculator.models;

import com.gescof.springbootrestcalculator.models.enums.OperationType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class OutputDto {
    private OperationType operationType;
    private BigDecimal firstDecimal;
    private BigDecimal secondDecimal;
    private BigDecimal result;
}
