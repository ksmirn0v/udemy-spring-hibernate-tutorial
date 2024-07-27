package com.ksmirnov.demoapp.dao;

import com.ksmirnov.demoapp.model.Account;

import java.util.List;

public interface MembershipDAO {

    void addMember();

    void setMember();

    void getMember();

    List<Account> findMembers();

}
