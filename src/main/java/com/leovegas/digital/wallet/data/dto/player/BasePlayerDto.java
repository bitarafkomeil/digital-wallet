package com.leovegas.digital.wallet.data.dto.player;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BasePlayerDto {
    @NotEmpty(message = "First Name Shouldn't Be Empty")
    private String firstName;
    @NotEmpty(message = "Last Name Shouldn't Be Empty")
    private String lastName;
    @NotEmpty(message = "User Name Shouldn't Be Empty")
    private String userName;
}