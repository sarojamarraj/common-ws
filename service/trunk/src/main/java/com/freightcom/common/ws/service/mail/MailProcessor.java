package com.freightcom.common.ws.service.mail;

import java.io.IOException;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author rnarine
 *
 */
@Component
public class MailProcessor {
	@Autowired
	private RulesEngine rulesEngine;
	
	@Autowired
	private Rules mailRequestRules;
	
	/**
	 * 
	 * @param mailRequest
	 */
	public void processRequest(MailRequest mailRequest) {
		// define facts
        Facts facts = new Facts();
        facts.put("mailrequest", mailRequest);

        // fire rules on known facts
        rulesEngine.fire(mailRequestRules, facts);
	}
	
	/**
	 * 
	 * @param jsonString
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void processRequest(String jsonString) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		MailRequest mailRequest = objectMapper.readValue(jsonString, MailRequest.class);
		processRequest(mailRequest);
	}
	
	public void setRulesEngine(RulesEngine rulesEngine) {
		this.rulesEngine = rulesEngine;
	}
	
	public void setMailRequestRules(Rules mailRequestRules) {
		this.mailRequestRules = mailRequestRules;
	}
}