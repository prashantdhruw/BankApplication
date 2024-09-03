package com.bankx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.bankx.exception.BankException;



@Entity
public class Customer {
    @Id
    private String id;
    private String name;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Account currentAccount;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Account savingsAccount;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactionHistory = new ArrayList<>();

    public Customer() {
        this.id = UUID.randomUUID().toString();
    }
    
    public Customer(String name) {
        this();
        this.name = name;
        this.currentAccount = new Account("Current");
        this.savingsAccount = new Account("Savings");
        creditSavingsAccountBonus();
    }

    private void creditSavingsAccountBonus() {
        savingsAccount.credit(500.0);
        addTransaction(new Transaction("Joining Bonus", 500.0, "Savings"));
    }

    public void transferToSavings(double amount) {
        if (currentAccount.getBalance() >= amount) {
            currentAccount.debit(amount);
            savingsAccount.credit(amount);
            addTransaction(new Transaction("Transfer to Savings", amount, currentAccount.getAccountId()));
            addTransaction(new Transaction("Transfer from Current", amount, savingsAccount.getAccountId()));
        } else {
            throw BankException.insufficientFunds("Current");
        }
    }

    public void transferToCurrent(double amount) {
        if (savingsAccount.getBalance() >= amount) {
            savingsAccount.debit(amount);
            currentAccount.credit(amount);
            addTransaction(new Transaction("Transfer to Current", amount, savingsAccount.getAccountId()));
            addTransaction(new Transaction("Transfer from Savings", amount, currentAccount.getAccountId()));
        } else {
            throw BankException.insufficientFunds("Savings");
        }
    }

    public void makePayment(double amount, String recipientAccount) {
        double fee = amount * 0.0005; // 0.05% fee
        double totalAmount = amount + fee;
        
        if (currentAccount.getBalance() >= totalAmount) {
            currentAccount.debit(totalAmount);
            addTransaction(new Transaction("Payment to " + recipientAccount, amount, currentAccount.getAccountId()));
            addTransaction(new Transaction("Transaction fee", fee, currentAccount.getAccountId()));
        } else {
            throw BankException.insufficientFunds("Current");
        }
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Account getCurrentAccount() { return currentAccount; }
    public void setCurrentAccount(Account currentAccount) { this.currentAccount = currentAccount; }
    public Account getSavingsAccount() { return savingsAccount; }
    public void setSavingsAccount(Account savingsAccount) { this.savingsAccount = savingsAccount; }
    public List<Transaction> getTransactionHistory() { return transactionHistory; }
    public void setTransactionHistory(List<Transaction> transactionHistory) { this.transactionHistory = transactionHistory; }
}