package com.ksmirnov.demoapp.dao;

import com.ksmirnov.demoapp.entity.Employer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployerDAOImpl implements EmployerDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployerDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employer> findAll() {
        TypedQuery<Employer> query = entityManager.createQuery("FROM Employer", Employer.class);
        return query.getResultList();
    }

    @Override
    public Employer findById(int id) {
        return entityManager.find(Employer.class, id);
    }

    @Override
    public Employer save(Employer employer) {
        return entityManager.merge(employer);
    }

    @Override
    public void deleteById(int id) {
        Employer employer = entityManager.find(Employer.class, id);
        entityManager.remove(employer);
    }
}
