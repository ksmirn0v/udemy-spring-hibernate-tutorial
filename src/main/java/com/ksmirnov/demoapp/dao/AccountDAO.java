package com.ksmirnov.demoapp.dao;

import com.ksmirnov.demoapp.model.Account;

import java.util.List;

public interface AccountDAO {

    void addAccount();

    void addAccount(Account account);

    void addAccount(Account account, boolean isExternal);

    List<Account> findAccounts();
}
