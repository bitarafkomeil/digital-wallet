package com.leovegas.digital.wallet.mapper;


import com.leovegas.digital.wallet.data.dto.transaction.CreateTransactionDto;
import com.leovegas.digital.wallet.data.dto.transaction.TransactionDto;
import com.leovegas.digital.wallet.data.dto.transaction.UpdateTransactionDto;
import com.leovegas.digital.wallet.data.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for {@link Transaction} and its DTOs.
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper extends BaseMapper<CreateTransactionDto, UpdateTransactionDto, TransactionDto, Transaction> {

    @Mapping(source = "transactionId", target = "id")
    @Mapping(source = "playerId", target = "player.id")
    Transaction fromCreateDTO(CreateTransactionDto dto);

    @Mapping(target = "transactionId", source = "id")
    @Mapping(target = "playerId", source = "player.id")
    @Mapping(target = "playerUserName", source = "player.userName")
    TransactionDto toDto(Transaction entity);
}