package com.smartbank.transactions;

import com.smartbank.utils.Logger;

public class UPITransaction implements TransactionProcessor, Logger {
    private String upiId;
    private boolean success;

    public UPITransaction(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void process(double amount) {
        if (TransactionProcessor.validate(amount)) {
            logInfo("Processing UPI payment of ₹" + amount + " via " + upiId);
            success = true;
        } else {
            logInfo("Invalid UPI amount");
            success = false;
        }
    }

    @Override
    public boolean refund(double amount) {
        if (success && amount < 1000) {
            logInfo("Refunded ₹" + amount + " to UPI ID: " + upiId);
            return true;
        }
        return false;
    }
}