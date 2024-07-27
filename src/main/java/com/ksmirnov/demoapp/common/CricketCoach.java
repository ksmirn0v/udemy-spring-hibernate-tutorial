package com.ksmirnov.demoapp.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("In constructor: " + this.getClass().getSimpleName());
    }

    @PostConstruct
    public void runAfterInitialization() {
        System.out.println("In runAfterInitialization(): " + this.getClass().getSimpleName());
    }

    @PreDestroy
    public void runBeforeCleanup() {
        System.out.println("In runBeforeCleanup(): " + this.getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
