package com.freightcom.common.ws.service.mail;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freightcom.common.dto.mail.AddToList;
import com.freightcom.common.dto.mail.MailRecipient;
import com.freightcom.common.dto.mail.MailRequest;
import com.freightcom.common.dto.mail.SendMail;

public class BaseMailMessageTest {
	protected String toJSON(MailRequest mailRequest) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String s = objectMapper.writeValueAsString(mailRequest);
		return s;
	}
	
	protected MailRequest createSendMailRequest() {
		SendMail sendMail = new SendMail();
		MailRecipient from = new MailRecipient("rnarine@shipfreightcom.com", "Ravi Narine");
		sendMail.setFrom(from);
		sendMail.setSubject("Hello");
		MailRecipient to = new MailRecipient("ranarine@rogers.com", "Ravi");
		sendMail.addToRecipient(to);
		sendMail.setTemplate("test-template");
		sendMail.addTemplateValue("name", "Test Name");
		MailRequest mailRequest = new MailRequest();
		mailRequest.setSendMail(sendMail);
		return mailRequest;
	}
	
	protected MailRequest toMailRequest(String jsonString) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		MailRequest mailRequest = objectMapper.readValue(jsonString, MailRequest.class);
		return mailRequest;
	}
	
	protected MailRequest createAddToListRequest() {
		AddToList addToList = new AddToList("rnarine@freightcom.com", "Ravi", "Narine","e0820f1c2d");
		MailRequest mailRequest = new MailRequest();
		mailRequest.setAddToList(addToList);
		return mailRequest;
	}
}