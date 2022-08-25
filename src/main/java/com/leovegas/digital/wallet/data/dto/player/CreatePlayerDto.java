package com.leovegas.digital.wallet.data.dto.player;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class CreatePlayerDto extends BasePlayerDto {
    @Min(value = 0, message = "Minimum Value Of Opening Balance Is Zero")
    private double openingBalance;
}