package com.bankx.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private double amount;
    private String accountId;
    private Date timestamp;

    public Transaction() {}

    public Transaction(String description, double amount, String accountId) {
        this.description = description;
        this.amount = amount;
        this.accountId = accountId;
        this.timestamp = new Date();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
}