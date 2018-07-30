package tic_tac_toe.repository;

import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import tic_tac_toe.entity.Player;
import tic_tac_toe.repository.common.CommonRepository;

/**
 * JPA repository to work with {@link Player} entity
 *
 */
public interface PlayerRepository extends CommonRepository<Player> {
    Optional<Player> findByLogin(@NotEmpty String login);
}
