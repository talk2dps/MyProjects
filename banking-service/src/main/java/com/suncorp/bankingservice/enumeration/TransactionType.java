package com.suncorp.bankingservice.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

import static com.suncorp.bankingservice.util.Constants.*;

/**
 * Transaction type enumeration with values CREDIT and DEBIT.
 *
 * @author Debi Prasad Sahoo(755703)
 * @version 1.0
 */
public enum TransactionType {
    CREDIT(TRANSACTION_TYPE_CREDIT),
    DEBIT(TRANSACTION_TYPE_DEBIT),
    TRANSFER(TRANSCACTION_TYPE_TRANSFER);

    private String value;

    /**
     * Constructs TransactionType with value.
     *
     * @param value Value of TransactionType
     */
    TransactionType(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Custom Serialization for AccountType which can used for validation purpose.
     *
     * @param value Value of AccountType
     * @return AccountType
     * @throws IllegalArgumentException If other value other than values defined in enum is assigned to the variable,
     *                                  then IllegalArgumentException is thrown
     */
    @JsonCreator
    public static TransactionType create(String value) {
        if(value == null) {
            return null;
        }
        for(TransactionType v : values()) {
            if(value.equals(v.getValue())) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "TransactionType{" +
                "value=" + value +
                '}';
    }
}
