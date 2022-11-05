package com.gescof.springbootrestcalculator.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class OutputOperationDto {
    private List<OutputOperationElementDto> operationsList;
    private Long totalResults;
    private Integer pageNumber;
    private Integer pageSize;
}
