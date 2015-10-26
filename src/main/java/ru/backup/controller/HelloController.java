package ru.backup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.backup.domain.FileForm;
import ru.backup.domain.TaskForClient;
import ru.backup.domain.user.User;
import ru.backup.service.FileFormService;
import ru.backup.service.TaskFromServerService;
import ru.backup.service.user.UserService;

@Controller
public class HelloController {
	
	@Autowired
	private UserService  userService;
	
	@Autowired
	private FileFormService fileFormService;
	
	@Autowired
	private TaskFromServerService taskFromServerService;

	@RequestMapping("/hello")
	String hello()
	{
		Integer _____ = 04234;
		return "hello";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin()
	{
		List<User> findAll = userService.findAll();
		return "login";
	}
	
	@RequestMapping(value = "/admin/", method = RequestMethod.GET)
	public ModelAndView getAdmin(){
		ModelAndView model = new ModelAndView("admin");
		List<TaskForClient> findAll = taskFromServerService.findAll();
		model.addObject("files", findAll);
		return model;
	}
}
