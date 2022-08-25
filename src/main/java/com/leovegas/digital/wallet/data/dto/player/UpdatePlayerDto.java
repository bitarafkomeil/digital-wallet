package com.leovegas.digital.wallet.data.dto.player;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class UpdatePlayerDto extends BasePlayerDto {
    private long id;
    @Min(value = 0, message = "Minimum Value Of Balance Is Zero")
    private double balance;
}