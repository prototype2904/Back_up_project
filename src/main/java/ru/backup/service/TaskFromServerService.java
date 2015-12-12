package ru.backup.service;

import java.util.List;

import ru.backup.domain.FileForm;
import ru.backup.domain.TaskForClient;
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
	
	List<TaskForClient> findAll();
	
	void save(TaskFromServer taskFromServer);
	
	void delete(Long id);
	
	void edit(TaskFromServer taskFromServer);
	
	TaskFromServer findOneById(Long id);

	List<TaskFromServer> findAllByUser(User user);
	
	List<TaskForClient> findAllTasksForUser(User user);
	
	List<TaskForClient> findAllTasksForUserByFileForm(FileForm form);
	
	TaskForClient objectToForm(TaskFromServer taskFromServer);
	
}
