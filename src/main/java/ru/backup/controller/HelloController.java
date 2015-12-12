package ru.backup.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.backup.domain.FileForm;
import ru.backup.domain.TaskForClient;
import ru.backup.domain.TaskFromServer;
import ru.backup.domain.user.User;
import ru.backup.service.FileFormService;
import ru.backup.service.TaskFromServerService;
import ru.backup.service.user.UserService;

@Controller
public class HelloController {

	@Autowired
	private UserService userService;

	@Autowired
	private FileFormService fileFormService;

	@Autowired
	private TaskFromServerService taskFromServerService;

	@RequestMapping("/")
	String hello() {
		Integer _____ = 04234;
		return "hello";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Неверный логин или пароль");
		}

		if (logout != null) {
			model.addObject("msg", "Вы вышли из системы");
		}
		model.setViewName("login");

		return model;

	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Приветствую, " + user.getName() 
			+ ", к сожалению, у тебя нет прав для посещения этой страницы.");
		} else {
			model.addObject("msg", 
			"К сожалению, у тебя нет прав для посещения этой страницы!");
		}

		model.setViewName("403");
		return model;

	}



	@RequestMapping(value = "/admin/", method = RequestMethod.GET)
	public ModelAndView getAdmin(@RequestParam(required = false) BindingResult bindingResult) {
		ModelAndView model = new ModelAndView("admin");
		List<TaskForClient> findAll = taskFromServerService.findAll();
		model.addObject("tasks", findAll);
		model.addObject("taskForServer", new TaskFromServer());
		model.addObject("files", fileFormService.findAll());
		model.addObject("users", userService.findAllByRoleUser());
		return model;
	}

	@RequestMapping(value = "/admin/tasks/add/", method = RequestMethod.POST)
	public Object addTask(@ModelAttribute("task") @Valid TaskFromServer taskFromServer,  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors())
		{
			return "redirect:/admin/";
		}
		taskFromServerService.save(taskFromServer);

		return "redirect:/admin/";
	}

	@RequestMapping(value = "/admin/tasks/delete/{taskId}", method = RequestMethod.GET)
	public String deleteTask(@PathVariable Long taskId) {
		taskFromServerService.delete(taskId);
		return "redirect:/admin/";
	}

	@RequestMapping(value = "/admin/files/", method = RequestMethod.GET)
	public ModelAndView getAdminFiles() {
		ModelAndView model = new ModelAndView("admin");
		List<TaskForClient> findAll = taskFromServerService.findAll();
		model.addObject("files", findAll);
		model.addObject("task", new TaskFromServer());
		model.addObject("users", userService.findAllByRoleUser());
		return model;
	}

	@RequestMapping(value = "/admin/tasks/{userId}", method = RequestMethod.GET)
	public ModelAndView getUserTasks(@PathVariable Long userId) {
		ModelAndView model = new ModelAndView("admin");
		User user = userService.findOneById(userId);
		model.addObject("files", fileFormService.findAllUserFiles(user));
		model.addObject("taskForServer", new TaskFromServer());
		model.addObject("users", Arrays.asList(user));
		model.addObject("tasks", taskFromServerService.findAllByUser(user).stream()
				.map(object -> taskFromServerService.objectToForm(object)).collect(Collectors.toList()));
		return model;
	}

	@RequestMapping(value = "/admin/tasks/{filename}/{format}/{userId}", method = RequestMethod.GET)
	public ModelAndView getUserTaskFiles(@PathVariable Long userId, @PathVariable String filename,
			@PathVariable String format) {
		ModelAndView model = new ModelAndView("admin");
		User user = userService.findOneById(userId);
		FileForm form = new FileForm(filename, format, user, 1L);
		model.addObject("files", fileFormService.findAllVersions(form));
		model.addObject("taskForServer", new TaskFromServer());
		model.addObject("users", Arrays.asList(user));
		model.addObject("tasks", taskFromServerService.findAllTasksForUserByFileForm(form));
		return model;
	}

	@RequestMapping(value = "/admin/files/delete/{fileId}", method = RequestMethod.GET)
	public String deleteFile(@PathVariable Long fileId) {
		fileFormService.deleteOneById(fileId);
		return "redirect:/admin/";
	}
	
}
