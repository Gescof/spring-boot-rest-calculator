package com.gescof.springbootrestcalculator.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.gescof.calculator.api")
@Getter
@Setter
public class CalculatorProperties {
    private String basePath;
    private String sumPath;
    private String subtractionPath;
    private String multiplicationPath;
    private String divisionPath;
    private String operationsPath;
}
