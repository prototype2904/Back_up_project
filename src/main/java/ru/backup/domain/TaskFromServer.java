package ru.backup.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

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
	@NotNull(message="Пользователь должен быть указан")
	User user;
	
	/**
	 * Имя файла для отправки
	 */
	@Column
	@NotEmpty(message="Имя файла должно быть указано")
	private String filename;
	
	/**
	 * формат файла для отправки
	 */
	@Column
	@NotEmpty(message="Название формата должно быть указано")
	private String format;
	
	/**
	 * директория, в которой располагается файл
	 */
	@Column
	@NotEmpty(message="Директория файла должна быть указана")
	private String dirPath;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
