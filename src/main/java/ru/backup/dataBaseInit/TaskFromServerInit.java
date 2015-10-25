package ru.backup.dataBaseInit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import ru.backup.domain.TaskFromServer;
import ru.backup.service.TaskFromServerService;
import ru.backup.service.user.UserService;


/**
 * 
 * Начальная инициализация TaskFromServer в БД
 * 
 * @author Alexeevich Andrew
 *
 */
@Component
@DependsOn("userInit")
public class TaskFromServerInit {
		
	@Autowired
	private TaskFromServerService taskFromServerService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * Метод инициализации
	 */
	@PostConstruct
	private void init()
	{
		createTaskFromServer("roma", "C:/Users/Roman/Downloads/" , "1.11", "jpg");
		createTaskFromServer("roma", "C:/Users/Roman/Downloads/" , "Tede3uaDEgw", "jpg");
		createTaskFromServer("roma", "C:/Users/Roman/Downloads/" , "ucEG3Owp5pc", "jpg");
		
	}
	
	/**
	 * Метод добавления в базу данных
	 * 
	 * @param username 
	 * @param dirPath
	 * @param filename
	 * @param format
	 */
	private void createTaskFromServer(String username, String dirPath, String filename, String format)
	{
		TaskFromServer taskFromServer = new TaskFromServer();
		taskFromServer.setFilename(filename);
		taskFromServer.setUser(userService.findUserByUsername(username));
		taskFromServer.setDirPath(dirPath);
		taskFromServer.setFormat(format);
		taskFromServerService.save(taskFromServer);
	}
}
