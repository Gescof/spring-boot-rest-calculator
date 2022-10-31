package com.gescof.springbootrestcalculator.persistence.models;

import com.gescof.springbootrestcalculator.models.enums.OperationType;
import com.querydsl.core.annotations.QueryEntity;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@QueryEntity
@Document
@Getter
@Setter
public class EOperation {
    @Id
    private String id;
    private OperationType operationType;
    private BigDecimal firstDecimal;
    private BigDecimal secondDecimal;
    private BigDecimal result;
    @CreatedDate
    private DateTime createdOn;
    @LastModifiedDate
    private DateTime updatedOn;
}
