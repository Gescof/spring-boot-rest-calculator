package com.gescof.springbootrestcalculator.utils;

import com.gescof.springbootrestcalculator.models.outputs.OutputOperationElementDto;
import com.gescof.springbootrestcalculator.persistence.models.EOperation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OperationMapper {
    /**
     * Maps from output operation element DTO to operation Entity.
     *
     * @param outputOperationElementDto the DTO {@link OutputOperationElementDto} to map from
     * @return {@link EOperation} the Entity mapped
     */
    public static EOperation mapOutputDtoToEOperation(final OutputOperationElementDto outputOperationElementDto) {
        var operationEntity = new EOperation();
        BeanUtils.copyProperties(outputOperationElementDto, operationEntity);
        return operationEntity;
    }

    /**
     * Maps from operation Entity to output operation element DTO.
     *
     * @param operationEntity the Entity {@link EOperation} to map from
     * @return {@link OutputOperationElementDto} the DTO mapped
     */
    public static OutputOperationElementDto mapEOperationToOutputDto(final EOperation operationEntity) {
        var outputDto = OutputOperationElementDto.builder().build();
        BeanUtils.copyProperties(operationEntity, outputDto);
        return outputDto;
    }
}
