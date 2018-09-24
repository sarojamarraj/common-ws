package com.freightcom.common.ws.service.mail;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MailRecipient {
	private String email;
	private String name;
	
	public MailRecipient() {
	}
	
	public MailRecipient(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}
	
	public MailRecipient(String email) {
		super();
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}