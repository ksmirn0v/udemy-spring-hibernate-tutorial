package com.ksmirnov.demoapp.rest;

import com.ksmirnov.demoapp.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    // define private fields for the dependency
    private Coach coach;
    private Coach anotherCoach;

    // define a constructor for dependency injection
    @Autowired
    public MainController(
        @Qualifier("cricketCoach") Coach coach,
        @Qualifier("cricketCoach") Coach anotherCoach
    ) {
        System.out.println("In constructor: " + this.getClass().getSimpleName());
        this.coach = coach;
        this.anotherCoach = anotherCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return coach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check() {
        return "Comparing beans: coach == anotherCoach, " + (coach == anotherCoach);
    }
}
