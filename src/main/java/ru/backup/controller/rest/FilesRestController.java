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

import ch.qos.logback.core.encoder.ByteArrayUtil;
import ru.backup.domain.FileForm;
import ru.backup.domain.TaskFromServer;
import ru.backup.domain.user.CurrentUser;
import ru.backup.service.FileFormService;

/**
 * 
 * Рест контроллер для приема и отправки файлов
 * 
 * @author Chulanov Andrew
 *
 */
@RestController
@RequestMapping("/rest/files/")
public class FilesRestController {

	/**
	 * сервис для работы с файлами
	 */
	@Autowired
	private FileFormService fileFormService;

	/**
	 * 
	 * Загрузка файла на сервер
	 * 
	 * @param file
	 *            - принимаемый файл, массив по 4 бита
	 * @param filename
	 *            - имя файла
	 * @param format
	 *            - формат
	 * @param checksum
	 *            - хэш файла MD5
	 * @return
	 */
	@RequestMapping(value = "upload/", method = RequestMethod.POST)
	public String uploadFile(@RequestParam("file") byte[] file, @RequestParam("filename") String filename,
			@RequestParam("format") String format, @RequestParam("checksum") String checksum) {

		try {
			String hash = fileFormService.getChecksum(file);
			if (checksum.equals(hash)) {
				fileFormService.save(new FileForm(filename, format, checksum), file);
				return filename + "." + format + " was saved";
			} else {
				return "File came with errors";
			}

		} catch (IOException e) {
			return null;
		}
	}
	
	@RequestMapping(value = "download/", method = RequestMethod.GET)
	public byte[] downloadFile(@RequestParam("filename") String filename,
			@RequestParam("format") String format, @RequestParam("version") String version){
		
		return null;
	}
}