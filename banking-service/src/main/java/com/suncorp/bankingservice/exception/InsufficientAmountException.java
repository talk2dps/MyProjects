package com.suncorp.bankingservice.exception;

/**
 * This Exception is thrown when the amount to withdraw or trasfer is greater than the amount present in the account.
 *
 * @author Debi Prasad Sahoo(755703)
 * @version 1.0
 */
public class InsufficientAmountException extends Exception{
    private String message;

    public InsufficientAmountException(String message){
        this.message=message;
    }

    /**
     * Gets the error message
     *
     * @return The error message
     */
    public String getMessage(){
        return message;
    }
}
