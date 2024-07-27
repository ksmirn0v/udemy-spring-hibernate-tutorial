package com.ksmirnov.demoapp.rest;

import com.ksmirnov.demoapp.entity.Employee;
import com.ksmirnov.demoapp.exception.EmployeeErrorResponse;
import com.ksmirnov.demoapp.exception.EmployeeNotFoundException;
import com.ksmirnov.demoapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    private EmployeeService employeeService;

    @Autowired
    public MainController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + id);
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee save(@RequestBody Employee employee) {
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("/employees")
    public Employee update(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + id);
        }
        employeeService.deleteById(id);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException exc) {

        EmployeeErrorResponse error = new EmployeeErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(Exception exc) {

        EmployeeErrorResponse error = new EmployeeErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
