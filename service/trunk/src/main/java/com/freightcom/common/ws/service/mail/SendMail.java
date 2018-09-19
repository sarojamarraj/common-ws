package com.freightcom.common.ws.service.mail;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.freightcom.common.ws.service.KeyValue;

public class SendMail {
	private String subject;
	private MailRecipient from;
	private List<MailRecipient> to;
	private String body = null;
	private String template = null;
	private List<KeyValue> templateValues = null;
	
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
	
	public List<KeyValue> getTemplateValues() {
		return templateValues;
	}
	
	public void setTemplateValues(List<KeyValue> templateValues) {
		this.templateValues = templateValues;
	}
	
	public void addTemplateValue(String key,Object value) {
		if(templateValues == null) {
			templateValues = new ArrayList<>();
		}
		
		templateValues.add(new KeyValue(key,value));
	}
	
	public String getTemplate() {
		return template;
	}
	
	public void setTemplate(String template) {
		this.template = template;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}