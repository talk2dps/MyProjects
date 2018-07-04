package com.suncorp.bankingservice.exception;

/**
 * This Exception is thrown when any input field is invalid.
 *
 * @author Debi Prasad Sahoo(755703)
 * @version 1.0
 */
public class InvalidInputException extends Exception {
    private String message;

    public InvalidInputException(String message){
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
