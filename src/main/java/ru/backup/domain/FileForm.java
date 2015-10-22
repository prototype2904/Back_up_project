package ru.backup.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ru.backup.domain.user.User;

/**
 * 
 * Описание файла, который хранится на сервере.
 * 
 * @author Chulanov Andrew
 *
 */
@Entity
public class FileForm implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String filename;

	@Column
	private String format;

	@ManyToOne
	@JoinColumn
	private User user;

	@Column
	private Long version;
	
	public FileForm() {
	}

	public FileForm(String filename, String format) {
		this.filename = filename;
		this.format = format;
	}

	public FileForm(String filename, String format, User user, Long version) {
		super();
		this.filename = filename;
		this.format = format;
		this.user = user;
		this.version = version;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
