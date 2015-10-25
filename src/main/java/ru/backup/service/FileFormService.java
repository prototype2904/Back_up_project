package ru.backup.service;

import java.io.IOException;
import java.util.List;

import ru.backup.domain.FileForm;
import ru.backup.domain.user.User;

/**
 * Сервис для обработки файлов на сервере
 * 
 * @author Chulanov Andrew
 *
 */
public interface FileFormService {
	
	/**
	 * Удалить описание файла из БД по id
	 * 
	 * @param id - идентификатор записи в БД
	 */
	void deleteOneById(Long id);
	
	/**
	 * Вернуть все версии конкретного файла на сервере
	 * @param fileForm - описание файла
	 * @return - список всех версий файла
	 */
	List<FileForm> findAllVersions(FileForm fileForm);
	
	/**
	 * Вернуть все описания версий файлов пользователя
	 * 
	 * @param user - пользователь
	 * @return - список всех описаний
	 */
	List<FileForm> findAllUserFiles(User user);
	
	/**
	 * Вернуть абсолютно все описания файлов из БД
	 * 
	 * @return - список описаний
	 */
	List<FileForm> findAll();
	
	/**
	 * Вернуть описание файла по идентификатору
	 * 
	 * @param id - идентификатор
	 * @return - описание файла
	 */
	FileForm findOne(Long id);

	/**
	 * Удалить все версии файлов, хранящиеся на сервере, подходящие под описание
	 * 
	 * @param fileForm - описание файла
	 */
	void deleteAllByFileForm(FileForm fileForm);

	/**
	 * 
	 * Сохранить файл на сервере, а описание в БД
	 * 
	 * @param fileForm - описание файла
	 * @param file - файл, массив по 4 бита
	 * @throws IOException
	 */
	void save(FileForm fileForm, byte[] file) throws IOException;
	
	/**
	 * Посчитать хэш файла. Алгоритм MD5
	 * 
	 * @param file - файл, массив по 4 бита
	 * @return - строка хэш файла
	 */
	String getChecksum(byte[] file);

}
