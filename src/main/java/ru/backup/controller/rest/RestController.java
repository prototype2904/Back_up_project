package ru.backup.controller.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.backup.domain.TaskFromServer;

/**
 * пример рестового контроллера
 * 
 * @author Roman
 *
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest/backup/")
public class RestController {

	/**
	 * пример метода GET возвращает объект
	 * @return
	 */
	@RequestMapping(value = "get/", method = RequestMethod.GET)
	public TaskFromServer get() {
		return new TaskFromServer();
	}

	/**
	 * примера метода POST 
	 * принимает объект и возвращает объект
	 * @param example
	 * @return
	 */
	@RequestMapping(value = "post/", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String post(@RequestBody TaskFromServer example) {
		return "good example";
	}
}
