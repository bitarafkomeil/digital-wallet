package com.leovegas.digital.wallet.data.repository;

import com.leovegas.digital.wallet.data.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Boolean existsByUserName(String userName);
}