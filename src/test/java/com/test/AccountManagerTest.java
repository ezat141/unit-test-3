package com.test;

import com.test.account.AccountManager;
import com.test.account.AccountManagerImpl;
import com.test.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountManagerTest {
    AccountManager accountManager = new AccountManagerImpl();

    @Test
    public void test1() {
        // Arrange
        double amount = 100.0;
        Customer customer = new Customer();
        customer.setBalance(200.0);

        // Act
        String result = accountManager.withdraw(customer, amount);

        Assertions.assertEquals(result, "success");

        Assertions.assertEquals(100.0, customer.getBalance());


    }
}
