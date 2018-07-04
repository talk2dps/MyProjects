package com.suncorp.bankingservice.model;

import com.suncorp.bankingservice.enumeration.TransactionType;
import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.Date;

/**
 * Transaction entity with the corresponding elements, constructors, getters and setters.
 *
 * @author Debi Prasad Sahoo(755703)
 * @version 1.0
 */
@Entity
@Scope(value = "prototype")
public class TransactionDetails extends ResourceSupport {
    @Id
    @GeneratedValue
    private long transactionId;

    @Column(nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    private long accountNumber;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private double closingBalanace;

    @Column(nullable = false)
    private Date transactionDate;

    @Column
    private String purpose;

    public TransactionDetails(long transactionId, TransactionType transactionType, long accountNumber, double amount,
                              double closingBalanace, Date transactionDate,String purpose) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.closingBalanace=closingBalanace;
        this.transactionDate = transactionDate;
        this.purpose=purpose;
    }

    public TransactionDetails() {
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getClosingBalanace() {
        return closingBalanace;
    }

    public void setClosingBalanace(double closingBalanace) {
        this.closingBalanace = closingBalanace;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}