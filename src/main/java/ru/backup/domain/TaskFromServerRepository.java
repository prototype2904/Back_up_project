package ru.backup.domain;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.backup.domain.user.User;

/**
 * 
 * Репозиторий для TaskFromServer
 * 
 * 
 * @author Alexeevich Andrew
 *
 */
public interface TaskFromServerRepository extends JpaRepository<TaskFromServer, Long> {

	/**
	 * Получить все задачи для пользователя
	 * @param user
	 * @return
	 */
	List<TaskFromServer> findAllByUser(User user);
	
	/**
	 * Получить все задачи
	 */
	List<TaskFromServer> findAll();
	
	/**
	 * Получить одну задачу по идентификатору
	 * @param id
	 * @return
	 */
	TaskFromServer findOneById(Long id);
	
}
