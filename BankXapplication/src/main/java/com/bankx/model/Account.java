package com.bankx.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.bankx.exception.BankException;

@Entity
public class Account {
    @Id
    private String accountId;
    private String type;
    private double balance;

    public Account() {
        this.accountId = UUID.randomUUID().toString();
    }

    public Account(String type) {
        this();
        this.type = type;
        this.balance = 0.0;
    }

    public void credit(double amount) {
        balance += amount;
        if (type.equals("Savings")) {
            double interest = balance * 0.005; // 0.5% interest
            balance += interest;
        }
    }

    public void debit(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw BankException.insufficientFunds(type);
        }
    }

    // Getters and setters
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}