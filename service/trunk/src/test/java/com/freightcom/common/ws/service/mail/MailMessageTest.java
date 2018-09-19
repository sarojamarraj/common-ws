package com.freightcom.common.ws.service.mail;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MailMessageTest {
	@Test
	public void unmarshallSendMailRequest() {
		SendMail sendMail = new SendMail();
		MailRecipient from = new MailRecipient("rnarine@shipfreightcom.com", "Ravi Narine");
		sendMail.setFrom(from);
		sendMail.setSubject("Hello");
		MailRecipient to = new MailRecipient("ranarine@rogers.com", "Ravi");
		sendMail.addToRecipient(to);
		MailRequest mailRequest = new MailRequest();
		mailRequest.setSendMail(sendMail);
		System.out.println(mailRequest);
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String s= objectMapper.writeValueAsString(mailRequest);
			System.out.println(s);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}