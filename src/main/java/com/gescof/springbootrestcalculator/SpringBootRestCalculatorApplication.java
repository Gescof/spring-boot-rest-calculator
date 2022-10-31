package com.gescof.springbootrestcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class SpringBootRestCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestCalculatorApplication.class, args);
    }

}
