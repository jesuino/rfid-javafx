package org.jugvale.peoplemanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The role of a person in case we want to use authorization
 * 
 * @author william
 * 
 */
@Entity
public class Role {
	@GeneratedValue
	@Id
	private long id;
	private String name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
