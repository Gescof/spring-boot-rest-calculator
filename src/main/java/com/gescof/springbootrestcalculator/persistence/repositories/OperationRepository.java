package com.gescof.springbootrestcalculator.persistence.repositories;

import com.gescof.springbootrestcalculator.persistence.models.EOperation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OperationRepository extends MongoRepository<EOperation, Long> {
}
