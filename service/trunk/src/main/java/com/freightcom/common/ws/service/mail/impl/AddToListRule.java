package com.freightcom.common.ws.service.mail.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freightcom.common.dto.mail.MailRequest;
import com.freightcom.common.ws.service.mail.MailService;

@Component
@Rule(name="AddToListRule")
public class AddToListRule {
	private final transient Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private MailService mailService;
	
	@Condition
	public boolean when(@Fact("mailrequest") MailRequest mailRequest) {
		return  mailRequest.getAddToList() != null;
	}
	
	@Action
	public void then(@Fact("mailrequest") MailRequest mailRequest) throws Exception {
		log.debug("AddToListRule Fired");
		mailService.addToList(mailRequest.getAddToList());
	}
}
