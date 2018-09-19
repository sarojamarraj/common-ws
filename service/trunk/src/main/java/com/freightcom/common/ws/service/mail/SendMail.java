package com.freightcom.common.ws.service.mail;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SendMail {
	private String subject;
	private MailRecipient from;
	private List<MailRecipient> to;
	private String body;
	
	public SendMail() {
		to = new ArrayList<>();
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public MailRecipient getFrom() {
		return from;
	}
	
	public void setFrom(MailRecipient from) {
		this.from = from;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public List<MailRecipient> getTo() {
		return to;
	}
	
	public void setTo(List<MailRecipient> to) {
		this.to = to;
	}
	
	public void addToRecipient(MailRecipient newTo) {
		to.add(newTo);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}