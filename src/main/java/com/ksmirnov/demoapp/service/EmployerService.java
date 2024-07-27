package com.ksmirnov.demoapp.service;

import com.ksmirnov.demoapp.entity.Employer;

import java.util.List;

public interface EmployerService {

    List<Employer> findAll();
    Employer findById(int id);
    Employer save(Employer employer);
    void deleteById(int id);
}
