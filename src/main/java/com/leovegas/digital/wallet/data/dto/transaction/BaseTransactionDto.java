package com.leovegas.digital.wallet.data.dto.transaction;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.leovegas.digital.wallet.data.dto.TransactionDeserializer;
import com.leovegas.digital.wallet.data.enumeration.TransactionType;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class BaseTransactionDto {

    @NotNull(message = "Transaction Id Shouldn't Be Empty")
    @Min(value = 1, message = "Minimum Value Of Transaction Id Is 1")
    private Long transactionId;

    @NotNull(message = "Transaction Type Shouldn't Be Empty")
    @JsonDeserialize(using = TransactionDeserializer.class)
    private TransactionType type;

    @NotNull(message = "Amount Shouldn't Be Empty")
    @Min(value = 1, message = "Minimum Value Of Amount Is 1")
    private double amount;

    @NotNull(message = "Player Id Shouldn't Be Empty")
    @Min(value = 1, message = "Minimum Value Of Player Id Is 1")
    private Long playerId;
}