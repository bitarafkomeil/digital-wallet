package com.leovegas.digital.wallet.data.enumeration;

import com.leovegas.digital.wallet.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum TransactionType {

    CREDIT("CREDIT"),
    DEBIT("DEBIT");

    private final String name;

    public static TransactionType find(String value) {
        for (TransactionType transactionType : values()) {
            if (Objects.isNull(transactionType.name)) continue;

            if (transactionType.name.equalsIgnoreCase(value)) return transactionType;
        }
        throw new NotFoundException("Transaction Type Doesn't Exist");
    }
}