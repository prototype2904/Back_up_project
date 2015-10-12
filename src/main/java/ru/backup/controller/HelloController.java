package ru.backup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/")
	String hello()
	{
		Integer _____ = 04234;
		return "hello";
	}
}
