package ru.backup.controller.rest;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ru.backup.domain.FileForm;
import ru.backup.domain.TaskFromServer;
import ru.backup.domain.user.CurrentUser;
import ru.backup.service.FileFormService;

/**
 * 
 * @author Chulanov Andrew
 *
 */
@RestController
@RequestMapping("/rest/files/")
public class FilesRestController {

	@Autowired
	private FileFormService fileFormService;

	@RequestMapping(value = "upload/", method = RequestMethod.POST)
	public String uploadFile(@RequestParam("file") byte[] file, @RequestParam("filename") String filename,
			@RequestParam("format") String format) {

		if (filename == null) {
			return "filename is null";
		}
		if (format == null) {
			return "format is null";
		}
		if (file == null) {
			return "file is null";
		}
		try {
			fileFormService.save(new FileForm(filename, format), file);
			return "good file";
		} catch (IOException e) {
			return "плохой файл\n" + e.getMessage();
		}
	}
}