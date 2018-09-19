package com.freightcom.common.ws.service.mail;

import org.junit.Ignore;
import org.junit.Test;

public class MailMessageTest extends BaseMailMessageTest {
	@Test
	@Ignore
	public void unmarshallSendMailRequest() {
		MailRequest mailRequest = createSendMailRequest();
		System.out.println(mailRequest);
		
		String s = null;
		try {
			s = toJSON(mailRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(s);
	}
	
	@Test
	@Ignore
	public void marshallSendMailRequest() {
		try {
			String s = toJSON(createSendMailRequest());
			MailRequest mailRequest = toMailRequest(s);
			System.out.println(mailRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void unmarshallAddToListRequest() {
		MailRequest mailRequest = createAddToListRequest();
		
		System.out.println(mailRequest);
		
		String s = null;
		try {
			s = toJSON(mailRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(s);
	}
	
	@Test
	public void marshallAddToListRequest() {
		try {
			String s = toJSON(createAddToListRequest());
			MailRequest mailRequest = toMailRequest(s);
			System.out.println(mailRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}