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

    // define a constructor for dependency injection
    @Autowired
    public MainController(@Qualifier("swimCoach") Coach coach) {
        System.out.println("In constructor: " + this.getClass().getSimpleName());
        this.coach = coach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return coach.getDailyWorkout();
    }
}
