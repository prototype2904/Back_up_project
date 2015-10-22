package ru.backup.service.user;

import java.util.List;
import java.util.Optional;

import ru.backup.domain.user.User;

/**
 * сервис для работы с пользователями
 * 
 * @author Roman
 *
 */
public interface UserService {
	
	/**
	 * находит пользователя по username
	 * @param username 
	 * @return
	 */
	User findUserByUsername(String username);
	
	/**
	 * находит всех пользователей в базе данных
	 * @return
	 */
	List<User> findAll();
	
	/**
	 * сохранить полльзователя в базе
	 * @param user
	 */
	void save(User user);
}
