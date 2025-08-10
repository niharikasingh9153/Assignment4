package com.smartbank.transactions;

public interface TransactionProcessor {
    void process(double amount);
    boolean refund(double amount);

    default void printReceipt() {
        System.out.println("Transaction completed.");
    }

    static boolean validate(double amount) {
        return amount > 0;
    }
}