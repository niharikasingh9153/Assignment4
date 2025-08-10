package com.smartbank.models;

public abstract class Account {
    protected String accountNumber;
    protected String customerName;
    protected double balance;

    public Account(String accountNumber, String customerName, double balance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
    }

    public abstract double calculateMonthlyInterest();

    public void printAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Balance: " + balance);
    }

    @Override
    public String toString() {
        return accountNumber + " | " + customerName + " | " + balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }
}