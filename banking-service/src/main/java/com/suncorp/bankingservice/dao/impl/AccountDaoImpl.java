package com.suncorp.bankingservice.dao.impl;

import com.suncorp.bankingservice.model.Account;
import com.suncorp.bankingservice.dao.AccountDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

/**
 * Implementation of AccountDao interface for interaction with database.
 *
 * @author  Debi Prasad Sahoo(755703)
 * @version 1.0
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext
    private EntityManager em;

    /**
     *Creates an account by adding the details to the database.
     *
     * @param accountDetails Account Details.
     * @return Account Details of the created account.
     */
    @Override
    public Account addAccount(Account accountDetails) {
        em.persist(accountDetails);
        return  accountDetails;
    }

    /**
     *Retrieves account details of the corresponding account number
     *
     * @param accountNumber Account Number.
     * @return Account details of the corresponding account number.
     */
    @Override
    public Account getAccount(long accountNumber) {
        return em.find(Account.class,accountNumber);
    }

    /**
     * Updates the account
     *
     * @param accountDetails Acount details to be updated in the database
     * @return Account Details of the updated account.
     */
    @Override
    public Account updateAccount(Account accountDetails) {
        em.lock(accountDetails, LockModeType.OPTIMISTIC);
        return em.merge(accountDetails);
    }
}
