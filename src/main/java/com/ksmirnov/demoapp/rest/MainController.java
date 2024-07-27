package com.ksmirnov.demoapp.rest;

import com.ksmirnov.demoapp.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    // define private fields for the dependency
    private Coach coach1_1; // baseball coach
    private Coach coach1_2; // baseball coach (checking scope)
    private Coach coach2; // swim coach
    private Coach coach3_1; // cricket coach
    private Coach coach3_2; // cricket coach (checking scope)

    // define a constructor for dependency injection
    @Autowired
    public MainController(
            Coach coach1_1,
            Coach coach1_2,
            @Qualifier("swimCoach") Coach coach2,
            @Qualifier("cricketCoach") Coach coach3_1,
            @Qualifier("cricketCoach") Coach coach3_2
    ) {
        System.out.println("In constructor: " + this.getClass().getSimpleName());
        this.coach1_1 = coach1_1;
        this.coach1_2 = coach1_2;
        this.coach2 = coach2;
        this.coach3_1 = coach3_1;
        this.coach3_2 = coach3_2;
    }

    @GetMapping("/baseball-coach-workout")
    public String getBaseballCoachWorkout() {
        return coach1_1.getDailyWorkout();
    }

    @GetMapping("/swim-coach-workout")
    public String getSwimCoachWorkout() {
        return coach2.getDailyWorkout();
    }

    @GetMapping("/cricket-coach-workout")
    public String getCricketCoachWorkout() {
        return coach3_1.getDailyWorkout();
    }
}
