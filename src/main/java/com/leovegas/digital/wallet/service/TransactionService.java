package com.leovegas.digital.wallet.service;

import com.leovegas.digital.wallet.data.dto.transaction.CreateTransactionDto;
import com.leovegas.digital.wallet.data.dto.transaction.TransactionDto;
import com.leovegas.digital.wallet.data.dto.transaction.UpdateTransactionDto;
import com.leovegas.digital.wallet.data.entity.Player;
import com.leovegas.digital.wallet.data.entity.Transaction;
import com.leovegas.digital.wallet.data.enumeration.TransactionType;
import com.leovegas.digital.wallet.data.repository.PlayerRepository;
import com.leovegas.digital.wallet.data.repository.TransactionRepository;
import com.leovegas.digital.wallet.exception.BadRequestException;
import com.leovegas.digital.wallet.exception.NotFoundException;
import com.leovegas.digital.wallet.mapper.TransactionMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService extends CrudService<Transaction, TransactionRepository,
        CreateTransactionDto, UpdateTransactionDto, TransactionDto, TransactionMapper> {
    private final PlayerRepository playerRepository;

    public TransactionService(TransactionRepository repository, TransactionMapper mapper, PlayerRepository playerRepository) {
        super(repository, mapper);
        this.playerRepository = playerRepository;
    }

    @Override
    protected void preCreate(Transaction entity) {
        // Checking That Transaction Id Does Not Exist
        if (repository.existsById(entity.getId())) throw new BadRequestException("Transaction Id Exist");

        // Checking That Player Id Is Correct An Get It
        Player player = playerRepository.findById(entity.getPlayer().getId())
                .orElseThrow(() -> new NotFoundException("Player Id Incorrect"));

        if (entity.getType().equals(TransactionType.DEBIT)) {
            if (player.getBalance() < entity.getAmount()) {
                throw new BadRequestException("Player Insufficient Balance");
            } else {
                player.setBalance(player.getBalance() - entity.getAmount());
            }
        } else {
            player.setBalance(player.getBalance() + entity.getAmount());
        }

        playerRepository.save(player);
    }

    public Page<TransactionDto> getAllTransaction(Pageable pageable, Optional<Long> playerId) {
        return repository.getAllByPlayerIdAndPageable(pageable, playerId)
                .map(mapper::toDto);
    }
}