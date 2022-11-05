package com.gescof.springbootrestcalculator.models;

import com.gescof.springbootrestcalculator.models.enums.OperationType;
import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

import java.math.BigDecimal;

@Builder
@Data
public class OutputOperationElementDto {
    private String id;
    private OperationType operationType;
    private BigDecimal firstDecimal;
    private BigDecimal secondDecimal;
    private BigDecimal result;
    private DateTime createdOn;
    private DateTime updatedOn;
}
