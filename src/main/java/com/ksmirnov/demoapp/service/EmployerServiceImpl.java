package com.ksmirnov.demoapp.service;

import com.ksmirnov.demoapp.dao.EmployerDAO;
import com.ksmirnov.demoapp.entity.Employer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {

    private EmployerDAO employerDAO;

    @Autowired
    public EmployerServiceImpl(EmployerDAO employerDAO) {
        this.employerDAO = employerDAO;
    }

    @Override
    public List<Employer> findAll() {
        return employerDAO.findAll();
    }

    @Override
    public Employer findById(int id) {
        return employerDAO.findById(id);
    }

    @Override
    @Transactional
    public Employer save(Employer employer) {
        return employerDAO.save(employer);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        employerDAO.deleteById(id);
    }
}
