package com.ksmirnov.demoapp.rest;

import com.ksmirnov.demoapp.entities.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    @GetMapping("/students")
    public List<Student> getStudents() {

        List<Student> students = new ArrayList<>();

        students.add(new Student("Denzel", "Waschington"));
        students.add(new Student("Leonardo", "DiCaprio"));
        students.add(new Student("Tom", "Hanks"));

        return students;
    }
}
