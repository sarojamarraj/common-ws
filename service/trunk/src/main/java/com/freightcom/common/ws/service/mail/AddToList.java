package com.freightcom.common.ws.service.mail;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AddToList {
	private String email;
	private String firstname;
	private String lastname;
	private String listId;
	
	public AddToList() {	
	}

	public AddToList(String email, String firstname, String lastname, String listId) {
		super();
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.listId = listId;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getListId() {
		return listId;
	}
	
	public void setListId(String listId) {
		this.listId = listId;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}