package com.test.account;

public class AccountManagerImpl implements AccountManager {
    private static final int MAX_CREDIT = 1000;

    @Override
    public void deposit(Customer customer, double amount) {
        customer.setBalance(customer.getBalance() + amount);

    }

    @Override
    public String withdraw(Customer customer, double amount) {
        double expectedBalance = customer.getBalance() - amount;

        if(expectedBalance < 0) {
            if(!customer.isCreditAllowed()){
                return "insufficient account balance";
            } else if(Math.abs(expectedBalance) > MAX_CREDIT && !customer.isVip()){
                return "maximum credit exceeded";
            }
        }
        customer.setBalance(expectedBalance);

        return "success";
    }
}
