package com.smartbank.models;

public class CurrentAccount extends Account {

    public CurrentAccount(String accountNumber, String customerName, double balance) {
        super(accountNumber, customerName, balance);
    }

    @Override
    public double calculateMonthlyInterest() {
        return balance * AccountType.CURRENT.getInterestRate() / 100 / 12;
    }
}