package com.freightcom.common.ws.service.mail;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MailRequest {
	private SendMail sendMail;
	
	public SendMail getSendMail() {
		return sendMail;
	}
	
	public void setSendMail(SendMail sendMail) {
		this.sendMail = sendMail;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}