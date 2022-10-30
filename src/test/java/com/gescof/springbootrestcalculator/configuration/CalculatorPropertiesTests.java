package com.gescof.springbootrestcalculator.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class CalculatorPropertiesTests {
    @Autowired
    private CalculatorProperties calculatorProperties;

    @Test
    void givenUserDefinedPOJO_whenBindingPropertiesFile_thenAllFieldsAreSet() {
        assertEquals("/api/v1/calculator", calculatorProperties.getBasePath());

        Map<String, String> expectedResourcesPath = new HashMap<>();
        expectedResourcesPath.put("sumPath", "/sum");
        expectedResourcesPath.put("subtractionPath", "/subtraction");
        expectedResourcesPath.put("multiplicationPath", "/multiplication");
        expectedResourcesPath.put("divisionPath", "/division");
        assertEquals(expectedResourcesPath.get("sumPath"), calculatorProperties.getSumPath());
        assertEquals(expectedResourcesPath.get("subtractionPath"), calculatorProperties.getSubtractionPath());
        assertEquals(expectedResourcesPath.get("multiplicationPath"), calculatorProperties.getMultiplicationPath());
        assertEquals(expectedResourcesPath.get("divisionPath"), calculatorProperties.getDivisionPath());
    }
}
