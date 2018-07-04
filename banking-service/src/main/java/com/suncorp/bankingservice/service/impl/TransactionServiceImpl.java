package com.suncorp.bankingservice.service.impl;

import com.suncorp.bankingservice.enumeration.TransactionType;
import com.suncorp.bankingservice.exception.InsufficientAmountException;
import com.suncorp.bankingservice.exception.InvalidInputException;
import com.suncorp.bankingservice.exception.NonExistentResourceException;
import com.suncorp.bankingservice.model.Account;
import com.suncorp.bankingservice.model.TransactionDetails;
import com.suncorp.bankingservice.dao.AccountDao;
import com.suncorp.bankingservice.dao.TransactionDao;
import com.suncorp.bankingservice.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Transaction service class used to perform operation such as add and view transactions.
 *
 * @author Debi Prasad Sahoo(755703)
 * @version 1.0
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    AccountDao accountDao;

    /**
     * Performs withdraw transaction.
     *
     * @param accountNumber Account Number.
     * @param transactionDetails Transaction details to be added.
     * @return Transaction details of the added transaction.
     * @throws InvalidInputException Invalid input/request
     * @throws InsufficientAmountException Insufficient fund for transaction
     * @throws NonExistentResourceException Account number does not exist
     */
    @Transactional
    public TransactionDetails withdrawAmount(long accountNumber, TransactionDetails transactionDetails) throws InvalidInputException, InsufficientAmountException, NonExistentResourceException {
        logger.info("Inside withdraw, account number-" + accountNumber + "  Transaction details-" + transactionDetails);
        if (transactionDetails.getAmount() == 0) {
            throw new InvalidInputException("Amount cannot be null or Zero");
        }
        Account account = accountDao.getAccount(accountNumber);
        if (account != null) {
            if (account.getBalance() >= transactionDetails.getAmount()) {
                account.setBalance(account.getBalance() - transactionDetails.getAmount());
                logger.debug("Balance::" + account.getBalance());
                account = accountDao.updateAccount(account);
                logger.info("Account balance after saved in DB::" + account.getBalance());

                transactionDetails.setTransactionType(TransactionType.DEBIT);
                transactionDetails.setAccountNumber(accountNumber);
                transactionDetails.setClosingBalanace(account.getBalance());
                transactionDetails.setTransactionDate(new Date());
                logger.debug("Transaction details before saving to DB::" + transactionDetails.toString());
                transactionDao.addTransaction(transactionDetails);
                logger.info("Transaction details after saving to DB::" + transactionDetails.toString());

                return transactionDetails;
            } else {
                throw new InsufficientAmountException("Insufficient balance for transaction.");
            }
        } else {
            throw new NonExistentResourceException("Account number does not exist");
        }
    }

    /**
     * Performs Deposit transaction.
     *
     * @param accountNumber Account Number.
     * @param transactionDetails Transaction details to be added.
     * @return Transaction details of the added transaction.
     * @throws InvalidInputException Invalid input/request
     * @throws NonExistentResourceException Account number does not exist
     */
    @Transactional
    public TransactionDetails depositAmount(long accountNumber, TransactionDetails transactionDetails)
            throws InvalidInputException, NonExistentResourceException {
        logger.info("Inside deposit, account number-" + accountNumber + "  Transaction details-" + transactionDetails);
        if (transactionDetails.getAmount() == 0) {
            throw new InvalidInputException("Amount cannot be null or Zero");
        }
        Account account = accountDao.getAccount(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + transactionDetails.getAmount());
            logger.debug("Balance::" + account.getBalance());
            accountDao.updateAccount(account);
            logger.info("Account balance after saved in DB::" + account.getBalance());

            transactionDetails.setTransactionType(TransactionType.CREDIT);
            transactionDetails.setAccountNumber(accountNumber);
            transactionDetails.setClosingBalanace(account.getBalance());
            transactionDetails.setTransactionDate(new Date());
            logger.debug("Transaction details before saving to DB::" + transactionDetails.toString());
            transactionDetails = transactionDao.addTransaction(transactionDetails);
            logger.info("Transaction details after saving to DB::" + transactionDetails.toString());

            return transactionDetails;
        } else {
            throw new NonExistentResourceException("Account number does not exist");
        }
    }

    /**
     * Performs transfer transaction.
     *
     * @param accountNumber Account Number.
     * @param transactionDetails Transaction details to be added.
     * @return Transaction details of the added transaction.
     * @throws InvalidInputException Invalid input/request
     * @throws InsufficientAmountException Insufficient fund for transaction
     * @throws NonExistentResourceException Account number does not exist
     */
    @Transactional
    public TransactionDetails transferFund(long accountNumber, TransactionDetails transactionDetails)
            throws InvalidInputException, InsufficientAmountException, NonExistentResourceException {
        TransactionDetails depositReqDetails = new TransactionDetails();
        depositReqDetails.setAmount(transactionDetails.getAmount());
        depositReqDetails.setAccountNumber(transactionDetails.getAccountNumber());

        withdrawAmount(accountNumber, transactionDetails);
        if (transactionDetails != null) {
            depositReqDetails.setPurpose("Deposit from Account No." + accountNumber);
            depositAmount(depositReqDetails.getAccountNumber(), depositReqDetails);
            return transactionDetails;
        } else {
            throw new RuntimeException("Error while transferring.");
        }
    }

    /**
     * Gets all transaction corresponding to the account number.
     *
     * @param accountNumber Account Number.
     * @return List of Transaction details corresponding to the account number.
     * @throws NonExistentResourceException Account number/Transaction details does not exist
     */
    @Override
    public List<TransactionDetails> getAllTransactions(long accountNumber) throws NonExistentResourceException {
        logger.info("Inside getAllTransactions, accountNumber"+accountNumber);
        Account account = accountDao.getAccount(accountNumber);
        if (account != null) {
            List<TransactionDetails> transactionDetailsList = transactionDao.findByAccNo(accountNumber);
            if (transactionDetailsList.isEmpty()) {
                throw new NonExistentResourceException("No transactions found for this account number.");
            } else {
                logger.info("Transaction List size::"+transactionDetailsList.size());
                return transactionDetailsList;
            }
        }else{
            throw new NonExistentResourceException("Account number does not exist");
        }
    }

    @Override
    public TransactionDetails getTransactions(long accountNumber, long transactionId)
            throws NonExistentResourceException {
        logger.info("Inside getAllTransactions, accountNumber"+accountNumber);
        Account account = accountDao.getAccount(accountNumber);
        if (account != null) {
            TransactionDetails transactionDetails = transactionDao.getTransaction(accountNumber,transactionId);
            if (transactionDetails!=null) {
                logger.info("Transaction details::"+transactionDetails.toString());
                return transactionDetails;
            } else {
                throw new NonExistentResourceException("No transactions found for this account number.");
            }
        }else{
            throw new NonExistentResourceException("Account number does not exist");
        }
    }
}

