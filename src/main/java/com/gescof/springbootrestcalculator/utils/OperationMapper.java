package com.gescof.springbootrestcalculator.utils;

import com.gescof.springbootrestcalculator.models.OutputDto;
import com.gescof.springbootrestcalculator.persistence.models.EOperation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OperationMapper {
    public static EOperation mapOutputDtoToEOperation(final OutputDto outputDto) {
        var operationEntity = new EOperation();
        BeanUtils.copyProperties(outputDto, operationEntity);
        return operationEntity;
    }

    public static OutputDto mapEOperationToOutputDto(final EOperation operationEntity) {
        var outputDto = OutputDto.builder().build();
        BeanUtils.copyProperties(operationEntity, outputDto);
        return outputDto;
    }
}
