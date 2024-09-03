package com.bankx.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankx.exception.BankException;
import com.bankx.model.Account;
import com.bankx.model.Customer;
import com.bankx.model.Transaction;
import com.bankx.repository.CustomerRepository;


@Service
public class BankService {
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private NotificationService notificationService;
    
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    @Transactional
    public Customer onboardCustomer(String name) {
        Customer customer = new Customer(name);
        return customerRepository.save(customer);
    }

    public Customer getCustomer(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> BankException.customerNotFound(id));
    }

    @Transactional
    public void transferToSavings(String customerId, double amount) {
        Customer customer = getCustomer(customerId);
        
        if (customer.getCurrentAccount().getBalance() < amount) {
            throw BankException.insufficientFunds("Current");
        }
        
        customer.transferToSavings(amount);
        customerRepository.save(customer);
        notificationService.sendNotification(customer.getId(), 
            customer.getTransactionHistory().get(customer.getTransactionHistory().size() - 1));
    }

    @Transactional
    public void transferToCurrent(String customerId, double amount) {
        Customer customer = getCustomer(customerId);
        
        if (customer.getSavingsAccount().getBalance() < amount) {
            throw BankException.insufficientFunds("Savings");
        }
        
        customer.transferToCurrent(amount);
        customerRepository.save(customer);
        notificationService.sendNotification(customer.getId(), 
            customer.getTransactionHistory().get(customer.getTransactionHistory().size() - 1));
    }

    @Transactional
    public void makePayment(String senderAccountId, String recipientAccountId, double amount) {
        Account senderAccount = findAccountById(senderAccountId);
        Account recipientAccount = findAccountById(recipientAccountId);
        
        Customer sender = findCustomerByAccountId(senderAccountId);
        Customer recipient = findCustomerByAccountId(recipientAccountId);
        
        double fee = amount * 0.0005; // 0.05% fee
        double totalAmount = amount + fee;
        
        if (senderAccount.getBalance() < totalAmount) {
            throw BankException.insufficientFunds(senderAccount.getType());
        }
        
        senderAccount.debit(totalAmount);
        recipientAccount.credit(amount);
        
        Transaction senderTransaction = new Transaction("Payment to " + recipientAccountId, -amount, senderAccountId);
        Transaction recipientTransaction = new Transaction("Payment from " + senderAccountId, amount, recipientAccountId);
        Transaction feeTransaction = new Transaction("Transaction fee", -fee, senderAccountId);
        
        sender.addTransaction(senderTransaction);
        sender.addTransaction(feeTransaction);
        recipient.addTransaction(recipientTransaction);
        
        customerRepository.save(sender);
        customerRepository.save(recipient);
        
        notificationService.sendNotification(sender.getId(), senderTransaction);
        notificationService.sendNotification(recipient.getId(), recipientTransaction);
    }

    private Account findAccountById(String accountId) {
        for (Customer customer : customerRepository.findAll()) {
            if (customer.getCurrentAccount().getAccountId().equals(accountId)) {
                return customer.getCurrentAccount();
            }
            if (customer.getSavingsAccount().getAccountId().equals(accountId)) {
                return customer.getSavingsAccount();
            }
        }
        throw new BankException("Account not found with id: " + accountId, "ACCOUNT_NOT_FOUND");
    }

    private Customer findCustomerByAccountId(String accountId) {
        for (Customer customer : customerRepository.findAll()) {
            if (customer.getCurrentAccount().getAccountId().equals(accountId) ||
                customer.getSavingsAccount().getAccountId().equals(accountId)) {
                return customer;
            }
        }
        throw new BankException("Customer not found for account id: " + accountId, "CUSTOMER_NOT_FOUND");
    }

    public List<Transaction> getCustomerTransactions(String customerId) {
        Customer customer = getCustomer(customerId);
        return customer.getTransactionHistory();
    }

    public String getCustomerBalance(String customerId) {
        Customer customer = getCustomer(customerId);
        double currentBalance = customer.getCurrentAccount().getBalance();
        double savingsBalance = customer.getSavingsAccount().getBalance();
        return String.format("Current Account (ID: %s): R%.2f, Savings Account (ID: %s): R%.2f",
            customer.getCurrentAccount().getAccountId(), currentBalance,
            customer.getSavingsAccount().getAccountId(), savingsBalance);
    }
}