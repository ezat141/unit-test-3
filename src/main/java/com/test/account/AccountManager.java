package com.test.account;

public interface AccountManager {

    void deposit(Customer customer, double amount);
    String withdraw(Customer customer, double amount);
}
