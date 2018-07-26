package tic_tac_toe.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Common jpa repository that provides common operations with entities including paging, sorting and filtering
 *
 * @param <Entity> entity type
 */
@NoRepositoryBean
public interface CommonRepository<Entity> extends JpaRepository<Entity, Long>, JpaSpecificationExecutor<Entity> {}
