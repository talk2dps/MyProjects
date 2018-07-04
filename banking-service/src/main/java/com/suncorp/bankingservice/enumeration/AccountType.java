package com.suncorp.bankingservice.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static com.suncorp.bankingservice.util.Constants.*;

/**
 * Account type enumeration with values Savings and current.
 *
 * @author Debi Prasad Sahoo(755703)
 * @version 1.0
 */

public enum AccountType {
    SAVINGS(ACCOUNT_TYPE_SAVINGS),
    CURRENT(ACCOUNT_TYPE_CURRENT);

    private String value;

    /**
     * Constructs AccountType with value.
     *
     * @param value Value of AccountType
     */
    AccountType(String value){
        this.value=value;
    }

    /**
     * Gets the value of AccountType
     *
     * @return The value of AccountType
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of AccountType
     *
     * @param value Value of AccountType
     */
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
    public static AccountType create(String value) {
        if(value == null) {
            return null;
        }
        for(AccountType v : values()) {
            if(value.equals(v.getValue())) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Fetches the String value of AccountType Enum
     *
     * @return The String value of AccountType Enum
     */
    @Override
    public String toString() {
        return "AccountType{" +
                "value=" + value +
                '}';
    }
}
