package com.suncorp.bankingservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.suncorp.bankingservice.enumeration.AccountType;
import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Account Entity with the corresponding elements, constructors, getters and setters.
 *
 * @author Debi Prasad Sahoo(755703)
 * @version 1.0
 */
@Entity
@Scope(value = "prototype")
@SequenceGenerator(name="accountSeq", initialValue=1)
public class Account extends ResourceSupport{

    @Id
    @GeneratedValue
    private Long accountNumber;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(nullable = false)
    private Date dateOfBirth;

    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(nullable = false)
    private Date accountCreationDate;

    @JsonFormat(pattern="dd-MM-yyyy")
    @Version
    @Column(nullable = false)
    private Date accountUpdatedDate;

    @Column(nullable = false)
    private AccountType accountType;

    @Column(nullable = false)
    private double balance;

    public Account(Long accountNumber, String firstName, String lastName, Date dateOfBirth, Date accountCreationDate, Date accountUpdatedDate, AccountType accountType, double balance) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.accountCreationDate = accountCreationDate;
        this.accountUpdatedDate=accountUpdatedDate;
        this.accountType = accountType;
        this.balance = balance;
    }

    public Account() {
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(Date accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public Date getAccountUpdatedDate() {
        return accountUpdatedDate;
    }

    public void setAccountUpdatedDate(Date accountUpdatedDate) {
        this.accountUpdatedDate = accountUpdatedDate;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", accountCreationDate=" + accountCreationDate +
                ", accountUpdatedDate=" + accountUpdatedDate +
                ", accountType=" + accountType +
                ", balance=" + balance +
                '}';
    }
}
