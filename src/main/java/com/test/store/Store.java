package com.test.store;

import com.test.account.Customer;

public interface Store {
    void buy(Product product, Customer customer);
}
