package ru.backup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.backup.domain.user.User;
import ru.backup.service.user.UserService;

@Controller
public class HelloController {
	
	@Autowired
	private UserService  userService;

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
}
