package ru.backup.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest/backup/")
public class RestController {
	
	@RequestMapping(value="get/", method = RequestMethod.GET)
	public String get()
	{
		return "good example";
	}
}
