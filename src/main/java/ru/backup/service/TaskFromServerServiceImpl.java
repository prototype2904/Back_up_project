package ru.backup.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.backup.domain.FileForm;
import ru.backup.domain.TaskForClient;
import ru.backup.domain.TaskFromServer;
import ru.backup.domain.TaskFromServerRepository;
import ru.backup.domain.user.Role;
import ru.backup.domain.user.User;
import ru.backup.service.user.UserService;

@Service("taskFromServerService")
public class TaskFromServerServiceImpl implements TaskFromServerService {

	@Autowired
	private TaskFromServerRepository taskFromServerRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private FileFormService fileFormService;

	@Override
	public List<TaskForClient> findAll() {

		User user = userService.getCurrentUser();

		if (user.getRole() == Role.ADMIN) {
			List<TaskForClient> findAll = taskFromServerRepository.findAll().stream()
					.map(object -> objectToForm(object)).collect(Collectors.toList());
			return findAll;
		}
		if (user.getRole() == Role.USER) {
			List<TaskForClient> findAll = findAllTasksForUser(user);
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

	@Override
	public List<TaskForClient> findAllTasksForUser(User user) {
		return findAllByUser(user).stream().map(object -> objectToForm(object)).collect(Collectors.toList());
	}

	@Override
	public TaskForClient objectToForm(TaskFromServer taskFromServer) {
		TaskForClient taskForClient = new TaskForClient();
		taskForClient.setTaskFromServer(taskFromServer);
		List<FileForm> forms = fileFormService.findAllVersions(
				new FileForm(taskFromServer.getFilename(), taskFromServer.getFormat(), taskFromServer.getUser(), 1L));
		List<String> fileChecksums = forms.stream().map(object -> object.getChecksum()).collect(Collectors.toList());
		taskForClient.setFileChecksums(fileChecksums);
		return taskForClient;
	}

}
