package com.gescof.springbootrestcalculator.persistence.repositories;

import com.gescof.springbootrestcalculator.persistence.models.EOperation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OperationRepository extends MongoRepository<EOperation, String>, QuerydslPredicateExecutor<EOperation> {
}
