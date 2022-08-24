package com.gescof.springbootrestcalculator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class CalculatorConfiguration {
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
