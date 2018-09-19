package com.freightcom.common.ws.service.mail;

import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.junit.Before;
import org.junit.Test;

import com.freightcom.common.ws.service.mail.rule.AddToListRule;
import com.freightcom.common.ws.service.mail.rule.SingleEmailRule;

public class MailMessageProcessorTest extends BaseMailMessageTest {
	private MailProcessor mailProcessor;
	
	@Before
	public void init() {
		mailProcessor = new MailProcessor();
		mailProcessor.setRulesEngine(new DefaultRulesEngine());

		SingleEmailRule singleEmailRule = new SingleEmailRule();
		AddToListRule addToListRule = new AddToListRule();
		Rules rules = new Rules();
        rules.register(singleEmailRule);
        rules.register(addToListRule);
        mailProcessor.setMailRequestRules(rules);
	}
	
	@Test
	public void singleEmailRuleTrue() {
		MailRequest mailRequest = createSendMailRequest();
		mailProcessor.processRequest(mailRequest);
	}
	
	@Test
	public void addToListRuleTrue() {
		MailRequest mailRequest = createAddToListRequest();
		mailProcessor.processRequest(mailRequest);
	}
}