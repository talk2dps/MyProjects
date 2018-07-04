package com.suncorp.bankingservice.controller;

import com.suncorp.bankingservice.exception.InsufficientAmountException;
import com.suncorp.bankingservice.exception.InvalidInputException;
import com.suncorp.bankingservice.exception.NonExistentResourceException;
import com.suncorp.bankingservice.model.TransactionDetails;
import com.suncorp.bankingservice.service.impl.AccountServiceImpl;
import com.suncorp.bankingservice.service.impl.TransactionServiceImpl;
import com.suncorp.bankingservice.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Transaction resource includes operations related to transactions such as making a transaction and viewing them.
 *
 * @author Debi Prasad Sahoo(755703)
 * @version 1.0
 */
@RestController
public class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private TransactionServiceImpl transactionService;

    /**
     * Deposits, withdraws and transfer amount from the account.
     *
     * @param accountNumber      Bank account number.
     * @param transactionDetails Details of the transaction.
     * @return Returns the transactional details if the transaction is successful
     * @throws InvalidInputException        Invalid input/request
     * @throws NonExistentResourceException Account Number does not Exist
     * @throws InsufficientAmountException Insufficient Amount in account for transaction
     */
    @PostMapping(value = "/accounts/{accountNumber}/transactions", consumes = "application/json",
            produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> transactFund(@PathVariable("accountNumber") long accountNumber,
                                    @RequestBody TransactionDetails transactionDetails)
            throws InvalidInputException, NonExistentResourceException, InsufficientAmountException {
        logger.info("Inside Controller transactions, Request::" + transactionDetails.toString());
        if(transactionDetails.getTransactionType().getValue().equalsIgnoreCase(Constants.TRANSACTION_TYPE_CREDIT)){
            transactionService.depositAmount(accountNumber, transactionDetails);
        }else if(transactionDetails.getTransactionType().getValue().equalsIgnoreCase(Constants.TRANSACTION_TYPE_DEBIT)){
            transactionService.withdrawAmount(accountNumber, transactionDetails);
        }else{
            transactionService.transferFund(accountNumber, transactionDetails);
        }
        Link selfLink=linkTo(methodOn(TransactionController.class).viewTransactions(accountNumber,
                transactionDetails.getTransactionId())).withSelfRel();
        Link accountLink=linkTo(methodOn(AccountController.class).getAccount(accountNumber)).withRel("account");
        transactionDetails.add(selfLink);
        transactionDetails.add(accountLink);
        logger.info("Transaction details to be returned as response::" + transactionDetails.toString());
        return new ResponseEntity<>(transactionDetails, HttpStatus.OK);
    }

    /**
     * Gets all the transaction corresponding to an account number.
     *
     * @param accountNumber Bank account number
     * @return Returns all transactional details corresponding to an account if successful.
     * @throws NonExistentResourceException Account Number does not Exist
     */
    @GetMapping(value = "/accounts/{accountNumber}/transactions", consumes = "application/json",
            produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> viewAllTransactions(@PathVariable("accountNumber") long accountNumber)
            throws NonExistentResourceException {
        logger.info("Inside Controller viewAllTransactions, Account number::" + accountNumber);
        List<TransactionDetails> transactionDetailsList = transactionService.getAllTransactions(accountNumber);
        for(TransactionDetails t: transactionDetailsList){
            Link selfLink=linkTo(methodOn(TransactionController.class).
                    viewTransactions(accountNumber,t.getTransactionId())).withSelfRel();
            t.add(selfLink);
        }
        Link allTransactionsLink=linkTo(methodOn(TransactionController.class).viewAllTransactions(accountNumber)).
                withRel("allTransactions");
        Link accountLink=linkTo(methodOn(AccountController.class).getAccount(accountNumber)).withRel("account");
        Resources<TransactionDetails> result=new Resources<TransactionDetails>(transactionDetailsList,allTransactionsLink);
        result.add(accountLink);
        logger.info("Transaction details to be returned as response::" + transactionDetailsList.toString());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     *
     * @param accountNumber Account Number
     * @param transactionId Transaction Id
     * @return Transaction details of the transaction id provided
     * @throws NonExistentResourceException Account Number does not Exist
     */
    @GetMapping(value = "/accounts/{accountNumber}/transactions/{transactionId}", consumes = "application/json",
            produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> viewTransactions(@PathVariable("accountNumber") long accountNumber,
                                       @PathVariable("transactionId") long transactionId)
            throws NonExistentResourceException {
        logger.info("Inside Controller viewTransactions, Account number::" + accountNumber);
        TransactionDetails transactionDetails = transactionService.getTransactions(accountNumber,transactionId);
        Link selfLink=linkTo(methodOn(TransactionController.class).viewTransactions(accountNumber,transactionId)).withSelfRel();
        transactionDetails.add(selfLink);
        logger.info("Transaction details to be returned as response::" + transactionDetails.toString());
        return new ResponseEntity<>(transactionDetails, HttpStatus.OK);
    }

}
