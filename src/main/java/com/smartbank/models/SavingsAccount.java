package com.smartbank.models;

public class SavingsAccount extends Account {

    public SavingsAccount(String accountNumber, String customerName, double balance) {
        super(accountNumber, customerName, balance);
    }

    @Override
    public double calculateMonthlyInterest() {
        return balance * AccountType.SAVINGS.getInterestRate() / 100 / 12;
    }

    public void applyCashback(String type) {
        if ("GROCERY".equalsIgnoreCase(type)) {
            balance += 20;
        }
    }

    public void applyCashback(String type, double percent) {
        if ("SHOPPING".equalsIgnoreCase(type)) {
            double cashback = balance * percent / 100;
            balance += cashback;
        }
    }
}