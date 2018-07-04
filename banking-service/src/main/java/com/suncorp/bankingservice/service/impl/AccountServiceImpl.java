package com.suncorp.bankingservice.service.impl;

import com.suncorp.bankingservice.controller.AccountController;
import com.suncorp.bankingservice.exception.InvalidInputException;
import com.suncorp.bankingservice.exception.NonExistentResourceException;
import com.suncorp.bankingservice.model.Account;
import com.suncorp.bankingservice.dao.AccountDao;
import com.suncorp.bankingservice.dao.TransactionDao;
import com.suncorp.bankingservice.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Account Service class used to perform operation such as create and update account.
 *
 * @author Debi Prasad Sahoo(755703)
 * @version 1.0
 */

@Service
public class AccountServiceImpl implements AccountService{

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TransactionDao transactionDao;

    /**
     *Creates a new bank account and saves the details in the dao.
     *
     * @param accountDetails Account details
     * @return Account details of the new account if successful, or
     *         an error message describing the failure.
     *@throws InvalidInputException Invalid input/request.
     */
    @Transactional
    public Account createAccount(Account accountDetails) throws InvalidInputException {
        logger.info("Inside createAccount, account details::" + accountDetails.toString());
            if(accountDetails.getFirstName()!=null && !accountDetails.getFirstName().trim().isEmpty()
                    && accountDetails.getLastName()!=null && !accountDetails.getLastName().trim().isEmpty()
                    && accountDetails.getDateOfBirth()!=null && accountDetails.getAccountType()!=null) {
                Date date = new Date();
                accountDetails.setAccountCreationDate(date);
                accountDetails.setAccountUpdatedDate(date);
                logger.debug("Account details to be saved::" + accountDetails.toString());
                accountDetails = accountDao.addAccount(accountDetails);
                logger.info("Account details after saved in DB::" + accountDetails.toString());
                return accountDetails;
            }else{
                throw new InvalidInputException("Invalid Input");
            }
    }

    /**
     *Updates the account type of the provided account number
     *
     * @param accountNumber Account Number
     * @param accountDetails Account details to be updated
     * @return Account details of the updated account if successful
     * @throws NonExistentResourceException Account number does not exist.
     */
    @Transactional
    public Account updateAccountType(long accountNumber, Account accountDetails) throws NonExistentResourceException {
        logger.info("Inside updateAccountType, account number-" + accountNumber + "  Account details-" + accountDetails);
        Account account = accountDao.getAccount(accountNumber);
        if (account != null) {
            account.setAccountType(accountDetails.getAccountType());
            account.setAccountUpdatedDate(new Date());
            logger.debug("Account details to be saved::" + accountDetails.toString());
            accountDao.updateAccount(account);
            logger.info("Account details after saved in DB::" + accountDetails.toString());
            return account;
        } else {
            throw new NonExistentResourceException("Account number does not exist");
        }
    }

    /**
     *Gets the account details of the account
     *
     * @param accountNumber Account Number
     * @return Account details of the requested account number.
     * @throws NonExistentResourceException Account number does not exist
     */
    @Override
    public Account getAccount(long accountNumber) throws NonExistentResourceException {
        logger.info("Inside getAccount, account number::"+accountNumber);
        Account account=accountDao.getAccount(accountNumber);
        if(account!=null) {
            return account;
        }else{
            throw new NonExistentResourceException("Account number does not exist");
        }
    }
}