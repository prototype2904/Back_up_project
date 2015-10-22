package ru.backup.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.backup.domain.TaskFromServer;
import ru.backup.domain.TaskFromServerRepository;
import ru.backup.domain.user.User;

@Service("taskFromServerService")
public class TaskFromServerServiceImpl implements TaskFromServerService{

	@Autowired
	private TaskFromServerRepository taskFromServerRepository;
	
	@Override
	public List<TaskFromServer> findAll() {
		
		return taskFromServerRepository.findAll();
	}

	@Override
	public void save(TaskFromServer taskFromServer) {
//		List<TaskFromServer> findAllOrderByGeneralIdDesc = taskFromServerRepository.findAllOrderByGeneralIdDesc();
//		Long newGeneralId = findAllOrderByGeneralIdDesc != null ? findAllOrderByGeneralIdDesc.get(0).getGeneralId() + 1 : 1L;
	//	taskFromServer.setGeneralId(newGeneralId);
		taskFromServer.setVersion(1L);
		taskFromServerRepository.save(taskFromServer);
	}

	@Override
	public void delete(Long id) {
		taskFromServerRepository.delete(id);
	}
	
	public void saveNewVersion(TaskFromServer taskFromServer)
	{
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
		return null;// taskFromServerRepository.findAllByUser(user);
	}

}
