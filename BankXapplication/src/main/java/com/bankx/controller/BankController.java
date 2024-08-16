package com.bankx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bankx.model.Customer;
import com.bankx.model.Transaction;
import com.bankx.service.BankService;

@RestController
@RequestMapping("/api/bank")
public class BankController {
    
    @Autowired
    private BankService bankService;

    @PostMapping("/customers")
    public ResponseEntity<Customer> onboardCustomer(@RequestParam String name) {
        Customer customer = bankService.onboardCustomer(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String id) {
        Customer customer = bankService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = bankService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/customers/{id}/transferToSavings")
    public ResponseEntity<String> transferToSavings(
            @PathVariable String id,
            @RequestParam double amount) {
        bankService.transferToSavings(id, amount);
        return ResponseEntity.ok("Transfer to Savings successful");
    }

    @PostMapping("/customers/{id}/transferToCurrent")
    public ResponseEntity<String> transferToCurrent(
            @PathVariable String id,
            @RequestParam double amount) {
        bankService.transferToCurrent(id, amount);
        return ResponseEntity.ok("Transfer to Current successful");
    }

    @PostMapping("/payment")
    public ResponseEntity<String> makePayment(
            @RequestParam String senderAccountId,
            @RequestParam String recipientAccountId,
            @RequestParam double amount) {
        bankService.makePayment(senderAccountId, recipientAccountId, amount);
        return ResponseEntity.ok("Payment successful");
    }


    @GetMapping("/customers/{id}/transactions")
    public ResponseEntity<List<Transaction>> getCustomerTransactions(@PathVariable String id) {
        List<Transaction> transactions = bankService.getCustomerTransactions(id);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/customers/{id}/balance")
    public ResponseEntity<String> getCustomerBalance(@PathVariable String id) {
        String balanceInfo = bankService.getCustomerBalance(id);
        return ResponseEntity.ok(balanceInfo);
    }
}