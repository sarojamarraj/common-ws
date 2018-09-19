package com.freightcom.common.ws.service.configuration;

import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.freightcom.common.ws.service.mail.rule.AddToListRule;
import com.freightcom.common.ws.service.mail.rule.SingleEmailRule;
import com.github.alexanderwe.bananaj.connection.MailChimpConnection;
import com.microtripit.mandrillapp.lutung.MandrillApi;

@PropertySources({
	@PropertySource(value="classpath:mailchimp.properties"),
	@PropertySource(value="classpath:mailchimp-overrides.properties",ignoreResourceNotFound=true)
})
@Configuration
public class ServiceConfig {
	@Value("${mailchimp.mandrill.apikey}")
	private String mandrillApiKey;
	
	@Value("${mailchimp.apikey}")
	private String mailChimpApiKey;
	
	@Bean
	public MandrillApi mandrillApi() {
		MandrillApi mandrillApi = new MandrillApi(mandrillApiKey);
		return mandrillApi;
	}
	
	@Bean
	public MailChimpConnection mailChimpConnection() {
		MailChimpConnection mailChimpConnection = new MailChimpConnection(mailChimpApiKey);
		return mailChimpConnection;
	}
	
	@Bean
	public RulesEngine rulesEngine() {
		RulesEngine rulesEngine = new DefaultRulesEngine();
		return rulesEngine;
	}
	
	@Bean
	public Rules mailRequestRules() {
		SingleEmailRule singleEmailRule = new SingleEmailRule();
		AddToListRule addToListRule = new AddToListRule();
		Rules rules = new Rules();
        rules.register(singleEmailRule);
        rules.register(addToListRule);
        return rules;
	}
}