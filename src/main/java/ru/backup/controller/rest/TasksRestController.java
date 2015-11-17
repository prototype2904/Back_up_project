package ru.backup.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.backup.domain.FileForm;
import ru.backup.domain.TaskForClient;
import ru.backup.domain.TaskFromServer;
import ru.backup.domain.user.CurrentUser;
import ru.backup.domain.user.User;
import ru.backup.service.FileFormService;
import ru.backup.service.TaskFromServerService;
import ru.backup.service.user.UserService;

/**
 * 
 * Рест контроллер для обработки запросов по задачам
 * 
 * @author Alexeevich Andrew
 *
 */
@RestController
@RequestMapping("/rest/tasks/")
public class TasksRestController {

	@Autowired
	private TaskFromServerService taskFromServerService;

	@Autowired
	private UserService userService;

	@Autowired
	private FileFormService fileFormService;

	@RequestMapping("{id}")
	public TaskFromServer get(@PathVariable Long generalId) {
		return taskFromServerService.findOneById(generalId);
	}

	/**
	 * Вернуть все файлы с последней версией для данного пользователя
	 * 
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value = "all/", method = RequestMethod.GET)
	public List<TaskForClient> getAll() {
		return taskFromServerService.findAll();
	}

	/**
	 * Вернуть все версии данного файла
	 * 
	 * @param generalId
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value = "versions/{generalId}", method = RequestMethod.GET)
	public List<TaskFromServer> getAllVersions(@PathVariable Long generalId) {
		return null;
	}

	@RequestMapping(value = "files/", method = RequestMethod.POST)
	public List<FileForm> getAllFiles(@RequestParam("username") String username) {
		User user = userService.findUserByUsername(username);
		List<FileForm> files = fileFormService.findAllUserFiles(user);
		return files;
	}

}
