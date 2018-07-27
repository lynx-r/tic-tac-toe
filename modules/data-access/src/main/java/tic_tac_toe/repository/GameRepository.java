package tic_tac_toe.repository;

import tic_tac_toe.entity.Game;
import tic_tac_toe.repository.common.CommonRepository;

/**
 * JPA repository to work with {@link Game} entity
 *
 */
public interface GameRepository extends CommonRepository<Game> {
}
