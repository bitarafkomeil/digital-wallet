package com.leovegas.digital.wallet.data.entity;

import com.leovegas.digital.wallet.data.enumeration.TransactionType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Transaction implements IdentifiableEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private TransactionType type;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date", columnDefinition = "date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;
}