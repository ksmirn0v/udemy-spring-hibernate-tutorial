package com.ksmirnov.demoapp.dao;

import com.ksmirnov.demoapp.model.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    @Override
    public void addAccount() {
        System.out.println(getClass() + ": adding an empty account");
    }

    @Override
    public void addAccount(Account account) {
        System.out.println(getClass() + ": adding an account - " + account);
    }

    @Override
    public void addAccount(Account account, boolean isExternal) {
        String external = isExternal ? "external " : "";
        System.out.println(getClass() + ": adding an " + external + "account - " + account);
    }

    @Override
    public List<Account> findAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("Jack", "Nicholson"));
        accounts.add(new Account("Frank", "Sinatra"));
        return accounts;
    }
}
