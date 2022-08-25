package com.leovegas.digital.wallet.data.dto.player;

import lombok.Data;

@Data
public class PlayerDto extends BasePlayerDto {
    private long id;
    private double balance;
}