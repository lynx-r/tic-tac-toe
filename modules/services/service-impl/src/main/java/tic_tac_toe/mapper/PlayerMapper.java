package tic_tac_toe.mapper;

import org.mapstruct.Mapper;
import tic_tac_toe.domain.player.PlayerDto;
import tic_tac_toe.entity.Player;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    Player toEntity(PlayerDto dto);

    PlayerDto toDto(Player player);
}
