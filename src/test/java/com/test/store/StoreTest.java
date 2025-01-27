package com.test.store;

import com.test.account.AccountManager;
import com.test.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoreTest {

    @Test
    void buyProductShouldWithdrawAmountFromCustomerAndDecreaseStock(){
        // Arrange

        Product product = new Product();
        product.setQuantity(4);
        product.setPrice(50.0);

        Customer customer = new Customer();
        Store store = new StoreImpl(new AlwaysSuccessAccountManager());

        // Act

        store.buy(product, customer);
        // Assert
        Assertions.assertEquals(3, product.getQuantity());

    }

    @Test
    void buyProductShouldGivePaymentError(){
        // Arrange
        Product product = new Product();
        product.setQuantity(4);
        product.setPrice(50.0);

        Customer customer = new Customer();
        Store store = new StoreImpl(new AlwaysFailAccountManager());
        // Act
        try {
            store.buy(product, customer);
            Assertions.fail("Should throw exception");
        } catch (Exception ex) {
            Assertions.assertEquals("Payment failure: failed", ex.getMessage());
        }

        // Assert

        Assertions.assertEquals(4, product.getQuantity());

    }
    static class AlwaysSuccessAccountManager implements AccountManager {
        @Override
        public void deposit(Customer customer, double amount) {

        }

        @Override
        public String withdraw(Customer customer, double amount) {
            return "success";
        }
    }

    static class AlwaysFailAccountManager implements AccountManager {
        @Override
        public void deposit(Customer customer, double amount) {

        }

        @Override
        public String withdraw(Customer customer, double amount) {
            return "failed";
        }
    }
}
