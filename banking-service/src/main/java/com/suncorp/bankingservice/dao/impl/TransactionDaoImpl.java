package com.suncorp.bankingservice.dao.impl;

import com.suncorp.bankingservice.model.TransactionDetails;
import com.suncorp.bankingservice.dao.TransactionDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Implementation of TransactionDao for interaction with database.
 *
 * @author  Debi Prasad Sahoo(755703)
 * @version 1.0
 */
@Repository
public class TransactionDaoImpl implements TransactionDao {

    @PersistenceContext
    private EntityManager em;

    /**
     * Adds a transaction to the transaction_details table
     *
     * @param transactionDetails Transaction Details to be added.
     * @return Transaction details of the added transaction.
     */
    @Override
    public TransactionDetails addTransaction(TransactionDetails transactionDetails) {
        em.persist(transactionDetails);
        return  transactionDetails;
    }

    /**
     * Gives the list of transaction details corresponding to the account number.
     *
     * @param accountNumber Account Number.
     * @return List of transaction details corresponding to the account number.
     */
    @Override
    public List<TransactionDetails> findByAccNo(long accountNumber) {

        String queryString = "SELECT t FROM TransactionDetails t where t.accountNumber=:accountNumber";
        Query query = em.createQuery(queryString,TransactionDetails.class).setParameter("accountNumber",accountNumber);

        return (List<TransactionDetails>) query.getResultList();
    }

    /**
     *Provides the transaction details of the provided transaction ID
     *
     * @param accountNumber Account number
     * @param transactionId Transaction ID
     * @return Transaction details of the provided transaction ID
     */
    @Override
    public TransactionDetails getTransaction(long accountNumber, long transactionId) {
        String queryString = "SELECT t FROM TransactionDetails t where t.accountNumber=:accountNumber " +
                "AND t.transactionId=:transactionId";
        Query query = em.createQuery(queryString,TransactionDetails.class).
                setParameter("accountNumber",accountNumber)
                .setParameter("transactionId",transactionId);

        return (TransactionDetails) query.getSingleResult();
    }
}
