package com.leovegas.digital.wallet.service;

import com.leovegas.digital.wallet.data.dto.player.CreatePlayerDto;
import com.leovegas.digital.wallet.data.dto.player.PlayerDto;
import com.leovegas.digital.wallet.data.dto.player.UpdatePlayerDto;
import com.leovegas.digital.wallet.data.entity.Player;
import com.leovegas.digital.wallet.data.repository.PlayerRepository;
import com.leovegas.digital.wallet.data.repository.TransactionRepository;
import com.leovegas.digital.wallet.exception.BadRequestException;
import com.leovegas.digital.wallet.mapper.PlayerMapper;
import org.springframework.stereotype.Service;

@Service
public class PlayerService extends CrudService<Player, PlayerRepository,
        CreatePlayerDto, UpdatePlayerDto, PlayerDto, PlayerMapper> {
    private final TransactionRepository transactionRepository;

    public PlayerService(PlayerRepository repository, PlayerMapper mapper, TransactionRepository transactionRepository) {
        super(repository, mapper);
        this.transactionRepository = transactionRepository;
    }

    @Override
    protected void preCreate(Player entity) {
        // Check Duplication Of UserName
        if (repository.existsByUserName(entity.getUserName()))
            throw new BadRequestException("Player With This User Name Exist");
    }

    @Override
    protected void preDelete(Long id) {
        // Check Player Has Transactions Or Not
        //TODO: We can change delete strategy, Adding Status Enum For Player And With Deleting -> Change The Status To DELETED
        if (transactionRepository.existsByPlayerId(id))
            throw new BadRequestException("Player Has Transactions, Delete Transactions First");
    }
}