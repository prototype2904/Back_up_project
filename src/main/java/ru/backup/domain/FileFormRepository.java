package ru.backup.domain;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.backup.domain.user.User;

/**
 * 
 * Репозиторий для FileForm
 * 
 * @author Chulanov Andrew
 *
 */
public interface FileFormRepository extends JpaRepository<FileForm, Long>{
	
	List<FileForm> findAllByFilenameAndFormatAndUserOrderByVersionDesc(String fileName, String format, User user);
	
	Optional<FileForm> findOneById(Long id);
	
	void deleteAllByFilenameAndFormatAndUser(String fileName, String format, User user);
	
	List<FileForm> findAllByUser(User user);
	
	FileForm findOneByUserAndFilenameAndFormatAndVersion(User user, String filename, String format, Long version);
	
}
