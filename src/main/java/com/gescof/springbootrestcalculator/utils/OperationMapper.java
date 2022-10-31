package com.gescof.springbootrestcalculator.utils;

import com.gescof.springbootrestcalculator.models.OutputElementDto;
import com.gescof.springbootrestcalculator.persistence.models.EOperation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OperationMapper {
    public static EOperation mapOutputDtoToEOperation(final OutputElementDto outputElementDto) {
        var operationEntity = new EOperation();
        BeanUtils.copyProperties(outputElementDto, operationEntity);
        return operationEntity;
    }

    public static OutputElementDto mapEOperationToOutputDto(final EOperation operationEntity) {
        var outputDto = OutputElementDto.builder().build();
        BeanUtils.copyProperties(operationEntity, outputDto);
        return outputDto;
    }
}
