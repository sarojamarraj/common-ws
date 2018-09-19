package com.freightcom.common.ws.service.mail;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MailRequest {
	private SendMail sendMail = null;
	private AddToList addToList = null;
	
	public SendMail getSendMail() {
		return sendMail;
	}
	
	public void setSendMail(SendMail sendMail) {
		this.sendMail = sendMail;
	}
	
	public AddToList getAddToList() {
		return addToList;
	}
	
	public void setAddToList(AddToList addToList) {
		this.addToList = addToList;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}