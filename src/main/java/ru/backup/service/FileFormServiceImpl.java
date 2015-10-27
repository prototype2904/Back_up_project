package ru.backup.service;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.backup.domain.ApplicationUrl;
import ru.backup.domain.FileForm;
import ru.backup.domain.FileFormRepository;
import ru.backup.domain.user.User;
import ru.backup.service.user.UserService;

/**
 * Реализация интерфейса FileFormService
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

		// Получить авторизированного пользователя
		User user = userService.getCurrentUser();

		// Имеется ли уже версия такого файла?
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
		fileFormRepository.delete(id);
	}

	@Override
	public void deleteAllByFileForm(FileForm fileForm) {
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

	/**
	 * Создать новый файл на сервере
	 * 
	 * @param fileForm
	 *            - форма сохраняемого файла
	 * @param file
	 *            - массив файла по 4 бита
	 * @throws IOException
	 */
	private void createFile(FileForm fileForm, byte[] file) throws IOException {
		// формат файла /{username}_{filename}_{version}.{format}
		Path p = Paths.get(String.format("%s/%s_%s_%d.%s", ApplicationUrl.FilesUrl.getUrl(),
				fileForm.getUser().getUsername(), fileForm.getFilename(), fileForm.getVersion(), fileForm.getFormat()));

		// если файл уже существует, то удалим его
		if (Files.isReadable(p)) {
			deleteFileFromServer(fileForm);
		}

		// создадим новый файл
		try (OutputStream out = new BufferedOutputStream(
				Files.newOutputStream(p, StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
			// запишем новый файл
			// склеиваем правые и левые 4 бита в байт и записываем в файл
			for (int i = 0; i < file.length; i += 2) {
				out.write((file[i] << 4) + file[i + 1]);
			}
		} catch (IOException x) {
			throw new IOException("Неудалось открыть файл или записать в него.");
		}
	}

	/**
	 * удаление файла с сервера
	 * 
	 * @param fileForm
	 *            - описание файла на сервере
	 */
	private void deleteFileFromServer(FileForm fileForm) {

		// формат файла '/{username}_{filename}_{version}.{format}'
		Path p = Paths.get(String.format("%s/%s_%s_%d.%s", ApplicationUrl.FilesUrl.getUrl(),
				fileForm.getUser().getUsername(), fileForm.getFilename(), fileForm.getVersion(), fileForm.getFormat()));

		// удалить файл
		try (OutputStream out = new BufferedOutputStream(
				Files.newOutputStream(p, StandardOpenOption.DELETE_ON_CLOSE, StandardOpenOption.APPEND))) {
		} catch (IOException x) {
			System.err.println(x);
		}
	}

	/**
	 * Удаление всех версий файловой формы
	 * 
	 * @param fileForm
	 *            - описание файла
	 */
	private void deleteAllFilesFromServer(FileForm fileForm) {
		List<FileForm> fileForms = fileFormRepository.findAllByFilenameAndFormatAndUserOrderByVersionDesc(
				fileForm.getFilename(), fileForm.getFormat(), fileForm.getUser());

		fileForms.stream().forEach(object -> deleteFileFromServer(object));
	}

	@Override
	public String getChecksum(byte[] file) {

		try {
			//объект дайджеста.  Алгоритм MD5
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");

			//получить хэш
			messageDigest.update(file);
			byte[] digest = messageDigest.digest();

			//перевести в строку
			String checksum = new String();
			for (int i = 0; i < digest.length; i++) {
				checksum += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
			}
			return checksum;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public byte[] downloadFile(FileForm fileForm) {
		
		
		return null;
	}
}
