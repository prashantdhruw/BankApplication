package com.bankx.exception;

public class BankException extends RuntimeException {
    private final String errorCode;

    public BankException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public static BankException customerNotFound(String customerId) {
        return new BankException("Customer not found with id: " + customerId, "CUSTOMER_NOT_FOUND");
    }

    public static BankException insufficientFunds(String accountType) {
        return new BankException("Insufficient funds in " + accountType + " Account", "INSUFFICIENT_FUNDS");
    }

}