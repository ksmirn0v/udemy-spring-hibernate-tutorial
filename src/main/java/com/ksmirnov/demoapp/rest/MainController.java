package com.ksmirnov.demoapp.rest;

import com.ksmirnov.demoapp.entities.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    private List<Student> students;

    @PostConstruct
    public void loadStudents() {
        students = new ArrayList<>();
        students.add(new Student("Denzel", "Waschington"));
        students.add(new Student("Leonardo", "DiCaprio"));
        students.add(new Student("Tom", "Hanks"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int id) {
        return students.get(id);
    }
}
