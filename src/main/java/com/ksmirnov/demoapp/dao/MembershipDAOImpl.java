package com.ksmirnov.demoapp.dao;

import com.ksmirnov.demoapp.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MembershipDAOImpl implements MembershipDAO {

    @Override
    public void addMember() {
        System.out.println(getClass() + ": adding a member");
    }

    @Override
    public void setMember() {
        System.out.println(getClass() + ": setting a member");
    }

    @Override
    public void getMember() {
        System.out.println(getClass() + ": getting a member");
    }

    @Override
    public List<Account> findMembers() {
        throw new RuntimeException(getClass() + ": a very bad thing happened");
    }
}
