package com.gescof.springbootrestcalculator.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import java.time.Clock;

@Configuration
@EnableCaching
@EnableMongoAuditing
public class CalculatorConfiguration {
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
