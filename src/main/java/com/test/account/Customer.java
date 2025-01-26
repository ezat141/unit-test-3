package com.test.account;

import lombok.Data;

@Data
public class Customer {
    private String name;
    private double balance;
    private boolean creditAllowed;
    private int maxCredit = 0;
    private boolean vip;
}
