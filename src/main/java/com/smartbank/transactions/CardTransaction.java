package com.smartbank.transactions;

import com.smartbank.utils.Logger;

public class CardTransaction implements TransactionProcessor, Logger {
    private String cardNumber;
    private boolean success;

    public CardTransaction(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void process(double amount) {
        if (TransactionProcessor.validate(amount)) {
            logInfo("Processing card payment of ₹" + amount + " from card " + cardNumber);
            success = true;
        } else {
            logInfo("Invalid card amount");
            success = false;
        }
    }

    @Override
    public boolean refund(double amount) {
        if (success && amount < 5000) {
            logInfo("Refunded ₹" + amount + " to card: " + cardNumber);
            return true;
        }
        return false;
    }
}