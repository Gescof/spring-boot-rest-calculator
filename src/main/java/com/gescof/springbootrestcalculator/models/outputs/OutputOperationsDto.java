package com.gescof.springbootrestcalculator.models.outputs;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@JsonRootName(value = "operations")
@JsonPropertyOrder({"totalResults", "pageNumber", "pageSize", "operationsList"})
public class OutputOperationsDto {
    private List<OutputOperationElementDto> operationsList;
    private Long totalResults;
    private Integer pageNumber;
    private Integer pageSize;
}
