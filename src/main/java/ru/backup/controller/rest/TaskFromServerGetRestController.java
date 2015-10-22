package ru.backup.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.backup.domain.TaskFromServer;
import ru.backup.domain.user.CurrentUser;
import ru.backup.service.user.TaskFromServerService;

@RestController
@RequestMapping("/rest/tasks/")
public class TaskFromServerGetRestController {
	
	@Autowired
	private TaskFromServerService taskFromServerService;
	
	@RequestMapping("{id}")
	public TaskFromServer get(@PathVariable Long generalId)
	{
		return taskFromServerService.findOneById(generalId);
	}
	
	/**
	 * найти все файлы с последней версией для данного пользователя
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value = "all/{generalId}", method = RequestMethod.GET)
	public List<TaskFromServer> getAll(@PathVariable Long generalId, CurrentUser currentUser)
	{

		return taskFromServerService.findAll();
	}
	
	/**
	 * получить все версии данного файла
	 * @param generalId
	 * @param currentUser
	 * @return
	 */
	@RequestMapping(value = "versions/{generalId}", method = RequestMethod.GET)
	public List<TaskFromServer> getAllVersions(@PathVariable Long generalId, CurrentUser currentUser)
	{
		return null;
	}
}