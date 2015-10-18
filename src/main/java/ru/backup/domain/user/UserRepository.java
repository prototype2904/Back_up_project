package ru.backup.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для Пользователя
 * @author Roman
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findOneByUsername(String username);

}
