package ru.backup.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Класс задача из сервера
 * 
 * Объект в базе данных 
 * 
 * @author Roman
 *
 */
@Entity
public class TaskFromServer {
	
	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String username;
	
	@Column
	private String filePath;
	
	@Column
	private String version;
	
	@Column
	private String generalId;


	public String getFilePaths() {
		return filePath;
	}

	public void setFilePaths(String filePath) {
		this.filePath = filePath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getGeneralId() {
		return generalId;
	}

	public void setGeneralId(String generalId) {
		this.generalId = generalId;
	}
}
