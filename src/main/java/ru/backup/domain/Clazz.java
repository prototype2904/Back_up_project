package ru.backup.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Clazz {
	
	@Id
	@GeneratedValue
	Long id;
	

}
