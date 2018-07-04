package com.suncorp.bankingservice.service;

import com.suncorp.bankingservice.exception.InvalidInputException;
import com.suncorp.bankingservice.exception.NonExistentResourceException;
import com.suncorp.bankingservice.model.Account;

/**
 * Account service interface.
 */
public interface AccountService {
    /**
     *Creates a new bank account and saves the details in the dao.
     *
     * @param accountDetails Account details
     * @return Account details of the new account if successful.
     * @throws InvalidInputException Invalid input/request.
     */
    Account createAccount(Account accountDetails)throws  InvalidInputException;

    /**
     *Updates the account type of the provided account number
     *
     * @param accountNumber Account Number
     * @param accountDetails Account details to be updated
     * @return Account details of the updated account if successful
     * @throws NonExistentResourceException Account number does not exist
     */
    Account updateAccountType(long accountNumber, Account accountDetails) throws NonExistentResourceException;

    /**
     * Gets the account details of the account
     *
     * @param accountNumber Account Number
     * @return Account details of the requested account number.
     * @throws NonExistentResourceException Account number does not exist
     */
    Account getAccount(long accountNumber)throws NonExistentResourceException;
}
