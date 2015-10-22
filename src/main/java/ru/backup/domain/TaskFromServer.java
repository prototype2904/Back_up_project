package ru.backup.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ru.backup.domain.user.User;


/**
 * Класс задача из сервера
 * 
 * Объект в базе данных 
 * 
 * @author Roman
 *
 */
@Entity
public class TaskFromServer implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn
	User user;
	
	@Column
	private String filePath;
	
	@Column
	private Long version;
	
	@Column
	private Long generalId;


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

	public User getUse() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getGeneralId() {
		return generalId;
	}

	public void setGeneralId(Long generalId) {
		this.generalId = generalId;
	}
}
