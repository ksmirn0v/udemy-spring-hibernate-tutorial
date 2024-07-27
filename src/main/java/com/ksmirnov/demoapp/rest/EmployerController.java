package com.ksmirnov.demoapp.rest;

import com.ksmirnov.demoapp.entity.Employer;
import com.ksmirnov.demoapp.exception.EmployerNotFoundException;
import com.ksmirnov.demoapp.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployerController {

    private EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/employers")
    public List<Employer> findAll() {
        return employerService.findAll();
    }

    @GetMapping("/employers/{id}")
    public Employer findById(@PathVariable int id) {
        Employer employee = employerService.findById(id);
        if (employee == null) {
            throw new EmployerNotFoundException("Employer id not found - " + id);
        }
        return employee;
    }

    @PostMapping("/employers")
    public Employer save(@RequestBody Employer employer) {
        employer.setId(0);
        return employerService.save(employer);
    }

    @PutMapping("/employers")
    public Employer update(@RequestBody Employer employee) {
        return employerService.save(employee);
    }

    @DeleteMapping("/employers/{id}")
    public void delete(@PathVariable int id) {
        Employer employer = employerService.findById(id);
        if (employer == null) {
            throw new EmployerNotFoundException("Employee id not found - " + id);
        }
        employerService.deleteById(id);
    }
}
