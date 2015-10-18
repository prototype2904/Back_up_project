package ru.backup.domain.user;

/**
 *  Роли пользователей
 * @author Roman
 *
 */
public enum Role {
	

	ADMIN("Admin"),
	USER("User");
	
	private String name;
	
	private Role(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}
