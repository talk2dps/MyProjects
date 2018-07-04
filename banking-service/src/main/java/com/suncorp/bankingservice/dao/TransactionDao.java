package com.suncorp.bankingservice.dao;

import com.suncorp.bankingservice.model.TransactionDetails;

import java.util.List;
/**
 * Transaction Data Access Object Interface
 *
 * @author  Debi Prasad Sahoo(755703)
 * @version 1.0
 */
public interface TransactionDao{

    /**
     * Adds a transaction to the transaction_details table
     *
     * @param transactionDetails Transaction Details to be added.
     * @return Transaction details of the added transaction.
     */
    TransactionDetails addTransaction(TransactionDetails transactionDetails);

    /**
     * Gives the list of transaction details corresponding to the account number.
     *
     * @param accountNumber Account Number.
     * @return List of transaction details corresponding to the account number.
     */
    List<TransactionDetails> findByAccNo(long accountNumber);

    /**
     *Provides the transaction details of the provided transaction ID
     *
     * @param accountNumber Account number
     * @param transactionId Transaction ID
     * @return Transaction details of the provided transaction ID
     */
    TransactionDetails getTransaction(long accountNumber,long transactionId);
}
