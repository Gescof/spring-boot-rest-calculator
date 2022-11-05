package com.gescof.springbootrestcalculator.utils;

import com.gescof.springbootrestcalculator.models.OutputOperationElementDto;
import com.gescof.springbootrestcalculator.persistence.models.EOperation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OperationMapper {
    public static EOperation mapOutputDtoToEOperation(final OutputOperationElementDto outputOperationElementDto) {
        var operationEntity = new EOperation();
        BeanUtils.copyProperties(outputOperationElementDto, operationEntity);
        return operationEntity;
    }

    public static OutputOperationElementDto mapEOperationToOutputDto(final EOperation operationEntity) {
        var outputDto = OutputOperationElementDto.builder().build();
        BeanUtils.copyProperties(operationEntity, outputDto);
        return outputDto;
    }
}
