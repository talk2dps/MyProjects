package com.suncorp.bankingservice.controller;

import com.suncorp.bankingservice.exception.InvalidInputException;
import com.suncorp.bankingservice.exception.NonExistentResourceException;
import com.suncorp.bankingservice.model.Account;
import com.suncorp.bankingservice.service.AccountService;
import com.suncorp.bankingservice.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


/**
 * Account resource includes operations related to account such as creation of account, updation of account type,
 * making transactions and viewing transactions.
 *
 * @author Debi Prasad Sahoo(755703)
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    /**
     * Creates a new account using the details sent by the user.
     *
     * @param accountDetails An object of type account.
     * @return Account details of the created account.
     * @throws InvalidInputException Invalid input
     * @throws NonExistentResourceException Account Number does not exist
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Account> createAccount(@RequestBody Account accountDetails) throws InvalidInputException,
            NonExistentResourceException {
        logger.info("Inside Controller createaccount, Request::" + accountDetails.toString());
        Account account = accountService.createAccount(accountDetails);
        Link selfLink=linkTo(methodOn(AccountController.class).getAccount(account.getAccountNumber()))
                .withSelfRel();
        Link transactionLink=linkTo(methodOn(TransactionController.class).
                viewAllTransactions(account.getAccountNumber())).withRel("allTransactions");
        account.add(selfLink);
        account.add(transactionLink);
        logger.info("Account details to be returned as response::" + account.toString());
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    /**
     * Provides the details of the account.
     *
     * @param accountNumber Account number
     * @return Account Details of the requested account number.
     * @throws NonExistentResourceException Account Number does not Exist
     */
    @GetMapping(value = "/{accountNumber}",consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<Account> getAccount(@PathVariable long accountNumber)
            throws NonExistentResourceException {
        logger.info("Inside controller getAccount, Account Number::"+accountNumber);
        Account account=accountService.getAccount(accountNumber);
        Link selfLink=linkTo(methodOn(AccountController.class).getAccount(account.getAccountNumber()))
                .withSelfRel();
        account.add(selfLink);
        logger.info("Account details to be returned as response::" + account.toString());
        return new ResponseEntity<Account>(account,HttpStatus.OK);
    }

    /**
     * Updates the account type
     *
     * @param accountNumber  Account Number of which the account type needs to be updated.
     * @param accountDetails Account details consisting account type
     * @return Returns the Account details corresponding to the account number.
     * @throws NonExistentResourceException Account Number does not Exist
     */
    @PatchMapping(value = "/{accountNumber}", consumes = "application/json", produces = "application/json")
    public @ResponseBody
        ResponseEntity<Account> updateAccountType(@PathVariable("accountNumber") Long accountNumber, @RequestBody Account accountDetails) throws NonExistentResourceException {
        logger.info("Inside Controller updateAccountType::account number-" + accountNumber + "  Account details-" + accountDetails);
        Account account = accountService.updateAccountType(accountNumber, accountDetails);
        Link selfLink=linkTo(methodOn(AccountController.class).getAccount(accountNumber)).withSelfRel();
        Link transactionLink=linkTo(methodOn(TransactionController.class).
                viewAllTransactions(account.getAccountNumber())).withRel("allTransactions");
        account.add(selfLink);
        account.add(transactionLink);
        logger.info("Account details to be returned as response::" + account.toString());
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

}