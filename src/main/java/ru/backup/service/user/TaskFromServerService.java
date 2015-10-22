package ru.backup.service.user;

import java.util.List;

import ru.backup.domain.TaskFromServer;
import ru.backup.domain.user.User;

/**
 * сервис для работы с заданиями для пользователей
 * 
 * 
 * @author Roman
 *
 */
public interface TaskFromServerService {
	
	List<TaskFromServer> findAll();
	
	void save(TaskFromServer taskFromServer);
	
	void delete(Long id);
	
	void edit(TaskFromServer taskFromServer);
	
	TaskFromServer findOneById(Long id);

	List<TaskFromServer> findAllByUser(User user);
}
