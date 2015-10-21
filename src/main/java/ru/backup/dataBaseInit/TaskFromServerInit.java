package ru.backup.dataBaseInit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import ru.backup.domain.TaskFromServer;
import ru.backup.domain.TaskFromServerRepository;
import ru.backup.service.user.TaskFromServerService;
import ru.backup.service.user.TaskFromServerServiceImpl;
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
		createTaskFromServer("roma", "C:/Users/Roman/Documents/" , "Crypt_lab2", "docx");
		createTaskFromServer("roma", "C:/Users/Roman/Documents/" , "Crypt_lab3", "docx");
		createTaskFromServer("roma", "C:/Users/Roman/Documents/" , "Crypt_lab4", "docx");
		
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
