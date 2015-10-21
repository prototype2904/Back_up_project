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

	/**
	 * Пользователь, для которого это задача 
	 */
	@ManyToOne
	@JoinColumn
	User user;
	
	/**
	 * Имя файла для отправки
	 */
	@Column
	private String filename;
	
	/**
	 * формат файла для отправки
	 */
	@Column
	private String format;
	
	/**
	 * директория, в которой располагается файл
	 */
	@Column
	private String dirPath;
	
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getDirPath() {
		return dirPath;
	}

	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	public User getUser() {
		return user;
	}
}
