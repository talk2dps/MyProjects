package com.suncorp.bankingservice.dao;

import com.suncorp.bankingservice.model.Account;

/**
 * Account Data Access Object Interface
 *
 * @author  Debi Prasad Sahoo(755703)
 * @version 1.0
 */
public interface AccountDao {
    /**
     *Creates an account by adding the details to the database.
     *
     * @param accountDetails Account Details.
     * @return Account Details of the created account.
     */
    Account addAccount(Account accountDetails);

    /**
     *Retrieves account details of the corresponding account number
     *
     * @param accountNumber Account Number.
     * @return Account details of the corresponding account number.
     */
    Account getAccount(long accountNumber);

    /**
     * Updates the account
     *
     * @param accountDetails Acount details to be updated in the database
     * @return Account Details of the updated account.
     */
    Account updateAccount(Account accountDetails);
}

