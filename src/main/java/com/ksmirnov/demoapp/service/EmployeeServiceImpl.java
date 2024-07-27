package com.ksmirnov.demoapp.service;

import com.ksmirnov.demoapp.dao.EmployeeRepository;
import com.ksmirnov.demoapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> employeeCandidate = employeeRepository.findById(id);
        if (employeeCandidate.isPresent()) {
            return employeeCandidate.get();
        }
        throw new RuntimeException("Did not find an employee with id = " + id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
