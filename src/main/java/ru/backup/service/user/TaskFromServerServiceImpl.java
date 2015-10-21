package ru.backup.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import ru.backup.domain.TaskFromServer;
import ru.backup.domain.TaskFromServerRepository;
import ru.backup.domain.user.Role;
import ru.backup.domain.user.User;

@Service("taskFromServerService")
public class TaskFromServerServiceImpl implements TaskFromServerService{

	@Autowired
	private TaskFromServerRepository taskFromServerRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<TaskFromServer> findAll() {
		
		User user = userService.getCurrentUser();
		
		if(user.getRole() == Role.ADMIN){
			List<TaskFromServer> findAll = taskFromServerRepository.findAll();		
			return findAll;
		}
		if(user.getRole() == Role.USER){
			List<TaskFromServer> findAll = taskFromServerRepository.findAllByUser(user);
			return findAll;
		}
		return null;
	}

	@Override
	public void save(TaskFromServer taskFromServer) {
		taskFromServerRepository.save(taskFromServer);
	}

	@Override
	public void delete(Long id) {
		taskFromServerRepository.delete(id);
	}
	
	@Override
	public void edit(TaskFromServer taskFromServer) {
		taskFromServerRepository.save(taskFromServer);
	}

	@Override
	public TaskFromServer findOneById(Long id) {
		return taskFromServerRepository.findOne(id);
	}

	@Override
	public List<TaskFromServer> findAllByUser(User user) {
		return taskFromServerRepository.findAllByUser(user);
	}

}
