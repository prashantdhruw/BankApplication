package com.bankx.service;

   import org.springframework.stereotype.Service;
   import com.bankx.model.Transaction;

   @Service
   public class NotificationService {
       public void sendNotification(String customerId, Transaction transaction) {
           System.out.println("Notification sent to customer " + customerId + ": " + 
                              transaction.getDescription() + " - Amount: " + transaction.getAmount());
       }
   }
   