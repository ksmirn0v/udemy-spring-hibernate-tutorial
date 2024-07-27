package com.ksmirnov.demoapp.config;

import com.ksmirnov.demoapp.common.Coach;
import com.ksmirnov.demoapp.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
