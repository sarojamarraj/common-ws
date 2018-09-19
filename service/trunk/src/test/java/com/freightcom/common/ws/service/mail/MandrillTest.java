package com.freightcom.common.ws.service.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.MergeVar;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;

public class MandrillTest {
	private MandrillApi mandrillApi;
	
	@Before
	public void init() {
		mandrillApi = new MandrillApi("pU22suPURIuLxcSJmYFaVQ");
	}
	
	@Test
	public void sendTemplateEmail() {
		/*Map<String,String> templateContent = new HashMap<>();
		templateContent.put("name", "Ravi Narine");*/
		MandrillMessage message = new MandrillMessage();
		message.setSubject("Hello World!");
		message.setFromEmail("rnarine@shipfreightcom.com");
		message.setFromName("Ravi Narine");
		MergeVar mergeVar = new MergeVar();
		mergeVar.setName("name");
		mergeVar.setContent("Ravi Narine");
		List<MergeVar> globalMergeVars = new ArrayList<>();
		globalMergeVars.add(mergeVar);
		message.setGlobalMergeVars(globalMergeVars);
		// add recipients
		ArrayList<Recipient> recipients = new ArrayList<Recipient>();
		Recipient recipient = new Recipient();
		recipient.setEmail("ranarine@rogers.com");
		recipient.setName("Ravi Narine");
		recipients.add(recipient);
		message.setTo(recipients);
		message.setPreserveRecipients(true);
		try {
			mandrillApi.messages().sendTemplate("test-new-user", null, message, false);
		} catch (MandrillApiError | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void renderTemplate() {
		Map<String,String> mergeVars = new HashMap<>();
		mergeVars.put("name", "Ravi Narine");
		String message;
		try {
			message = mandrillApi.templates().render("test-new-user", mergeVars, mergeVars);
			System.out.println("Message:"+message);
		} catch (MandrillApiError | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}