package com.suncorp.bankingservice.service;

import com.suncorp.bankingservice.exception.InsufficientAmountException;
import com.suncorp.bankingservice.exception.InvalidInputException;
import com.suncorp.bankingservice.exception.NonExistentResourceException;
import com.suncorp.bankingservice.model.TransactionDetails;

import java.util.List;

/**
 * Transaction service interface.
 */
public interface TransactionService {

    /**
     * Performs withdraw transaction.
     *
     * @param accountNumber Account Number.
     * @param transactionDetails Transaction details to be added.
     * @return Transaction details of the added transaction.
     * @throws InvalidInputException Invalid input/request
     * @throws InsufficientAmountException Insufficient fund for transaction
     * @throws NonExistentResourceException Account Number does not exist
     */
    TransactionDetails withdrawAmount(long accountNumber, TransactionDetails transactionDetails) throws InvalidInputException,
            InsufficientAmountException, NonExistentResourceException;

    /**
     * Performs Deposit transaction.
     *
     * @param accountNumber Account Number.
     * @param transactionDetails Transaction details to be added.
     * @return Transaction details of the added transaction.
     * @throws InvalidInputException Invalid input/request
     * @throws NonExistentResourceException Account Number does not exist
     */
    TransactionDetails depositAmount(long accountNumber, TransactionDetails transactionDetails)
            throws InvalidInputException,NonExistentResourceException;

    /**
     * Performs transfer transaction.
     *
     * @param accountNumber Account Number.
     * @param transactionDetails Transaction details to be added.
     * @return Transaction details of the added transaction.
     * @throws InvalidInputException Invalid input/request
     * @throws InsufficientAmountException Insufficient fund for transaction
     * @throws NonExistentResourceException Account Number does not exist
     */
    TransactionDetails transferFund(long accountNumber, TransactionDetails transactionDetails)
            throws InvalidInputException, InsufficientAmountException, NonExistentResourceException;

    /**
     * Gets all transaction corresponding to the account number.
     *
     * @param accountNumber Account Number.
     * @return List of Transaction details corresponding to the account number.
     * @throws NonExistentResourceException Account Number does not exist
     */
    List<TransactionDetails> getAllTransactions(long accountNumber) throws NonExistentResourceException;

    /**
     *Provides the transaction details of the provided transaction ID
     *
     * @param accountNumber Account number
     * @param transactionId Transaction ID
     * @return Transaction details of the provided transaction ID
     * @throws NonExistentResourceException Account Number does not exist
     */
    TransactionDetails getTransactions(long accountNumber,long transactionId) throws NonExistentResourceException;
}
