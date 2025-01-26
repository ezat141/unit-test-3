package com.test;

import com.test.account.AccountManager;
import com.test.account.AccountManagerImpl;
import com.test.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AccountManagerTest {
    AccountManager accountManager;
    Customer customer;

    @BeforeEach
    public void setUp() {
        accountManager = new AccountManagerImpl();
        customer = new Customer();
    }

    @Test
    public void depositShouldAddAmountToBalance(){
        // Arrange
        double amount = 50.0;
        customer.setBalance(50);
        // Act
        accountManager.deposit(customer, amount);

        // Assert
        Assertions.assertEquals(100.0, customer.getBalance());
    }

    @Test
    public void withdrawShouldSucceedIfPositiveBalance() {
        // Arrange
        double amount = 100.0;
        customer.setBalance(200.0);

        // Act
        String result = accountManager.withdraw(customer, amount);

        // Assert

        Assertions.assertEquals(result, "success");
        Assertions.assertEquals(100.0, customer.getBalance());
    }

    @Test
    public void  withdrawShouldFailIfInsufficientBalanceAndNoCreditAllowed() {
        // Arrange
        double amount = 200.0;
        customer.setBalance(100.0);
        customer.setCreditAllowed(false);

        // Act

        String result = accountManager.withdraw(customer, amount);

        // Assert

        Assertions.assertEquals(result, "insufficient account balance");
        Assertions.assertEquals(100.0, customer.getBalance());
    }

    @Test
    public void  withdrawShouldFailIfNegativeBalanceGreaterThanMaxCreditAndNotVipAndCreditAllowed() {
        // Arrange
        double amount = 6000.0;
        customer.setBalance(4000.0);
        customer.setVip(false);
        customer.setCreditAllowed(true);

        // Act

        String result = accountManager.withdraw(customer, amount);

        // Assert

        Assertions.assertEquals(result, "maximum credit exceeded");
        Assertions.assertEquals(4000.0, customer.getBalance());
    }

    @Test
    public void  withdrawShouldSucceedIfNegativeBalanceGreaterThanMaxCreditAndVipAndCreditAllowed() {
        // Arrange
        double amount = 6000.0;
        customer.setBalance(4000.0);
        customer.setVip(true);
        customer.setCreditAllowed(true);

        // Act

        String result = accountManager.withdraw(customer, amount);

        // Assert

        Assertions.assertEquals(result, "success");
        Assertions.assertEquals(-2000.0, customer.getBalance());
    }

    @Test
    public void  withdrawShouldSucceedIfNegativeBalanceLessThanMaxCreditAndVipAndCreditAllowed() {
        // Arrange
        double amount = 6000.0;
        customer.setBalance(5500.0);
        customer.setVip(true);
        customer.setCreditAllowed(true);

        // Act

        String result = accountManager.withdraw(customer, amount);

        // Assert

        Assertions.assertEquals(result, "success");
        Assertions.assertEquals(-500.0, customer.getBalance());
    }
}
