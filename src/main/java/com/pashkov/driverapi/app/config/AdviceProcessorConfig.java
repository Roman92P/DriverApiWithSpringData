package com.pashkov.driverapi.app.config;

import com.pashkov.driverapi.app.model.AdviceProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class
AdviceProcessorConfig {

    @Bean
    AdviceProcessor adviceProcessor(){
        return new AdviceProcessor();
    }
}
