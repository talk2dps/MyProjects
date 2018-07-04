package com.suncorp.bankingservice.exception;

/**
 * This exception is thrown when the resource which is requested for is not present.
 *
 * @author Debi Prasad Sahoo(755703)
 * @version 1.0
 */
public class NonExistentResourceException extends Exception{
    private String message;

    public NonExistentResourceException(String message){
        this.message=message;
    }

    /**
     * Gets the error message
     *
     * @return The error message.
     */
    public String getMessage(){
        return message;
    }
}
