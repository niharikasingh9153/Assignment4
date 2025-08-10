package com.smartbank;

import com.smartbank.models.*;
import com.smartbank.transactions.*;
import com.smartbank.utils.*;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    // Final constants
    public static final String BANK_NAME = "SmartBank";
    public static final String IFSC_CODE = "SMRT0001234";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User Input for Account Type
        System.out.println("Welcome to " + BANK_NAME);
        System.out.println("Enter Account Type (SAVINGS / CURRENT): ");
        String accountTypeInput = scanner.nextLine().toUpperCase();

        Account account;

        // Create Account Based on Input (Upcasting)
        switch (accountTypeInput) {
            case "SAVINGS":
                account = new SavingsAccount("SB123", "Alice", 10000);
                break;
            case "CURRENT":
                account = new CurrentAccount("CA456", "Bob", 20000);
                break;
            default:
                System.out.println("Invalid account type.");
                return;
        }

        account.printAccountDetails();
        System.out.println("Monthly Interest: ₹" + account.calculateMonthlyInterest());

        // Downcasting
        if (account instanceof SavingsAccount) {
            SavingsAccount sa = (SavingsAccount) account;
            sa.applyCashback("GROCERY");
            sa.applyCashback("SHOPPING", 2.0);
            System.out.println("Balance after cashback: ₹" + sa.getBalance());
        }

        // Enum and switch-case benefits
        System.out.println("\nAccount Benefits:");
        AccountType type = AccountType.valueOf(accountTypeInput);
        switch (type) {
            case SAVINGS:
                System.out.println("Earn 4% interest per annum.");
                break;
            case CURRENT:
                System.out.println("Free overdraft protection.");
                break;
            case LOAN:
                System.out.println("Low-interest personal loans.");
                break;
        }

        // Loop over all account types
        System.out.println("\nAvailable Account Types:");
        for (AccountType t : AccountType.values()) {
            System.out.println(t.name() + " - Interest Rate: " + t.getInterestRate() + "%");
        }

        // Functional Interface - Lambda (Simple & Compound Interest)
        InterestCalculator simpleInterest = (p, r, y) -> (p * r * y) / 100;
        InterestCalculator compoundInterest = (p, r, y) -> p * Math.pow((1 + r / 100), y) - p;

        System.out.println("\nSimple Interest: ₹" + simpleInterest.calculate(10000, 4, 1));
        System.out.println("Compound Interest: ₹" + compoundInterest.calculate(10000, 4, 1));

        // Transactions & Logging
        List<TransactionProcessor> transactions = new ArrayList<>();
        transactions.add(new UPITransaction("alice@upi"));
        transactions.add(new CardTransaction("1234-5678-9876-5432"));
        transactions.add(new UPITransaction("bob@upi"));

        // Process and Refund
        for (TransactionProcessor tp : transactions) {
            tp.process(500);
            tp.refund(200);
            tp.printReceipt();
        }

        // Stream API
        System.out.println("\n=== Stream API Results ===");

        // Print classes that refunded successfully
        System.out.println("Transactions with successful refund:");
        transactions.stream()
                .filter(tp -> tp.refund(200))
                .map(tp -> tp.getClass().getSimpleName())
                .distinct()
                .forEach(System.out::println);

        // Count UPI Transactions
        long upiCount = transactions.stream()
                .filter(tp -> tp instanceof UPITransaction)
                .count();
        System.out.println("Number of UPI Transactions: " + upiCount);

        // Sort and print all transaction class names
        List<String> sortedTransactionNames = transactions.stream()
                .map(tp -> tp.getClass().getSimpleName())
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Sorted Transaction Types:");
        sortedTransactionNames.forEach(System.out::println);

        // Utility method with varargs
        Utils.printMessages("\n=== System Logs ===",
                "Bank Name: " + BANK_NAME,
                "IFSC: " + IFSC_CODE,
                "Session Completed Successfully.");
    }
}