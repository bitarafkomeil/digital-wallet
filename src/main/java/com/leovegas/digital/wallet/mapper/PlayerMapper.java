package com.leovegas.digital.wallet.mapper;


import com.leovegas.digital.wallet.data.dto.player.CreatePlayerDto;
import com.leovegas.digital.wallet.data.dto.player.PlayerDto;
import com.leovegas.digital.wallet.data.dto.player.UpdatePlayerDto;
import com.leovegas.digital.wallet.data.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for {@link Player} and its DTOs.
 */
@Mapper(componentModel = "spring")
public interface PlayerMapper extends BaseMapper<CreatePlayerDto, UpdatePlayerDto, PlayerDto, Player> {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "openingBalance", target = "balance")
    Player fromCreateDTO(CreatePlayerDto dto);

    Player fromUpdateDTO(UpdatePlayerDto dto);

    PlayerDto toDto(Player entity);
}