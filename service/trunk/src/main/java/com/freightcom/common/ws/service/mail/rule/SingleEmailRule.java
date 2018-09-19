package com.freightcom.common.ws.service.mail.rule;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freightcom.common.ws.service.mail.MailRequest;
import com.freightcom.common.ws.service.mail.MailService;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;

@Component
@Rule(name="SingleEmailRule")
public class SingleEmailRule {
	private final transient Log log = LogFactory.getLog(getClass());

	@Autowired
	private MailService mailService;
	
	@Condition
	public boolean when(@Fact("mailrequest") MailRequest mailRequest) {
		return  mailRequest.getSendMail() != null;
	}
	
	@Action
	public void then(@Fact("mailrequest") MailRequest mailRequest) throws MandrillApiError, IOException {
		log.debug("SingleEmailRule Fired");
		mailService.sendSingleEmail(mailRequest.getSendMail());
	}
}
