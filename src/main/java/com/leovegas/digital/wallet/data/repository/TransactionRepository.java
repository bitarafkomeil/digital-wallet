package com.leovegas.digital.wallet.data.repository;

import com.leovegas.digital.wallet.data.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Boolean existsByPlayerId(Long playerId);

    Page<Transaction> findAllByPlayerId(Pageable pageable, Long playerId);

    default Page<Transaction> getAllByPlayerIdAndPageable(Pageable pageable, Optional<Long> playerId) {
        if (playerId.isEmpty())
            return findAll(pageable);
        else
            return findAllByPlayerId(pageable, playerId.get());
    }
}