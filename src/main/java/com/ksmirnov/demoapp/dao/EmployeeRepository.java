package com.ksmirnov.demoapp.dao;

import com.ksmirnov.demoapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}
