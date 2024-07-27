package com.ksmirnov.demoapp.dao;

import com.ksmirnov.demoapp.entity.Employer;

import java.util.List;

public interface EmployerDAO {

    List<Employer> findAll();
    Employer findById(int id);
    Employer save(Employer employer);
    void deleteById(int id);
}
