package ru.backup.service;

import java.io.IOException;
import java.util.List;

import ru.backup.domain.FileForm;
import ru.backup.domain.user.User;

/**
 * 
 * @author Chulanov Andrew
 *
 */
public interface FileFormService {
	
	void deleteOneById(Long id);
	
	List<FileForm> findAllVersions(FileForm fileForm);
	
	List<FileForm> findAllUserFiles(User user);
	
	List<FileForm> findAll();
	
	FileForm findOne(Long id);

	void deleteAllByFileForm(FileForm fileForm);

	void save(FileForm fileForm, byte[] file) throws IOException;

}
