package ru.backup.dataBaseInit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.backup.domain.TaskFromServer;
import ru.backup.domain.TaskFromServerRepository;
import ru.backup.service.user.TaskFromServerService;
import ru.backup.service.user.TaskFromServerServiceImpl;
import ru.backup.service.user.UserService;

@Component
public class TaskFromServerInit {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskFromServerInit.class);
	
	@Autowired
	private TaskFromServerService taskFromServerService;
	
	@Autowired
	private UserService userService;
	
	
	@PostConstruct
	private void init()
	{
	//	createTaskFromServer("roma", ".gwegmow/gwe/gw/eg/ew/" , 1);
	}
	
	private void createTaskFromServer(String username, String filepath, Long generalId)
	{
		TaskFromServer taskFromServer = new TaskFromServer();
		taskFromServer.setFilePath(filepath);
		taskFromServer.setGeneralId(generalId);
		taskFromServer.setUser(userService.findUserByUsername(username));
		taskFromServer.setVersion(1L);
		taskFromServerService.save(taskFromServer);
	}

}
