package ru.backup.service;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ru.backup.domain.ApplicationUrl;
import ru.backup.domain.FileForm;
import ru.backup.domain.FileFormRepository;
import ru.backup.domain.user.User;
import ru.backup.service.user.UserService;

/**
 * 
 * @author Chulanov Andrew
 *
 */
@Service
public class FileFormServiceImpl implements FileFormService {

	@Autowired
	private FileFormRepository fileFormRepository;

	@Autowired
	private UserService userService;

	@Override
	public void save(FileForm fileForm, byte[] file) throws IOException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUsername(auth.getName());

		List<FileForm> fileForms = fileFormRepository.findAllByFilenameAndFormatAndUserOrderByVersionDesc(
				fileForm.getFilename(), fileForm.getFormat(), user);

		// новый файл
		if (fileForms == null || fileForms.size() == 0) {
			fileForm.setUser(user);
			fileForm.setVersion(1L);
			createFile(fileForm, file);
			fileFormRepository.save(fileForm);
		}
		// новая версия файла
		else {
			// номер последней версии
			Long version = fileForms.get(0).getVersion();

			fileForm.setUser(user);
			fileForm.setVersion(version + 1);
			createFile(fileForm, file);
			fileFormRepository.save(fileForm);
		}

	}

	@Override
	public void deleteOneById(Long id) {
		// тут будет удаление файла

		fileFormRepository.delete(id);
	}

	@Override
	public void deleteAllByFileForm(FileForm fileForm) {
		// тут будет удаление файлов

		fileFormRepository.deleteAllByFilenameAndFormatAndUser(fileForm.getFilename(), fileForm.getFormat(),
				fileForm.getUser());

	}

	@Override
	public List<FileForm> findAllVersions(FileForm fileForm) {
		return fileFormRepository.findAllByFilenameAndFormatAndUserOrderByVersionDesc(fileForm.getFilename(),
				fileForm.getFormat(), fileForm.getUser());
	}

	@Override
	public List<FileForm> findAllUserFiles(User user) {
		return fileFormRepository.findAllByUser(user);
	}

	@Override
	public List<FileForm> findAll() {
		return fileFormRepository.findAll();
	}

	@Override
	public FileForm findOne(Long id) {
		return fileFormRepository.findOne(id);
	}
	
	private void createFile(FileForm fileForm, byte[] file) throws IOException{
		//формат файла /{username}_{filename}_{version}.{format}
		Path p = Paths.get(String.format("%s/%s_%s_%d.%s", ApplicationUrl.FilesUrl.getUrl(),
				fileForm.getUser().getUsername(), fileForm.getFilename(), fileForm.getVersion(), fileForm.getFormat()));
		try (OutputStream out = new BufferedOutputStream(
				Files.newOutputStream(p, StandardOpenOption.CREATE_NEW, StandardOpenOption.APPEND))) {
				out.write(file);
		} catch (IOException x) {
			throw new IOException("Неудалось открыть файл или записать в него.");
		}
	}

	private void deleteFileFromServer(FileForm fileForm) {
		
		//формат файла /{username}_{filename}_{version}.{format}
		Path p = Paths.get(String.format("%s/%s_%s_%d.%s", ApplicationUrl.FilesUrl.getUrl(),
				fileForm.getUser().getUsername(), fileForm.getFilename(), fileForm.getVersion(), fileForm.getFormat()));
		try (OutputStream out = new BufferedOutputStream(
				Files.newOutputStream(p, StandardOpenOption.DELETE_ON_CLOSE, StandardOpenOption.APPEND))) {
		} catch (IOException x) {
			System.err.println(x);
		}
	}

	private void deleteAllFilesFromServer(FileForm fileForm){
		List<FileForm> fileForms = fileFormRepository.findAllByFilenameAndFormatAndUserOrderByVersionDesc(fileForm.getFilename(), fileForm.getFormat(), fileForm.getUser());
		fileForms.stream().forEach(object -> deleteFileFromServer(object));
	}

}
