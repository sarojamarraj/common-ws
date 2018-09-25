package com.freightcom.common.ws.service;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.jeasy.rules.api.Rules;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import com.freightcom.common.ws.service.mail.impl.AddToListRule;
import com.freightcom.common.ws.service.mail.impl.SingleEmailRule;
import com.github.alexanderwe.bananaj.connection.MailChimpConnection;
import com.microtripit.mandrillapp.lutung.MandrillApi;

@PropertySources({
	@PropertySource(value="classpath:mailchimp.properties"),
	@PropertySource(value="classpath:mailchimp-overrides.properties",ignoreResourceNotFound=true),
	@PropertySource(value="classpath:queue.properties"),
	@PropertySource(value="classpath:queue-overrides.properties",ignoreResourceNotFound=true)
})
@Configuration
@ComponentScan({ "com.freightcom.common.ws.service" })
public class ServiceConfig {
	@Value("${mailchimp.mandrill.apikey}")
	private String mandrillApiKey;
	
	@Value("${mailchimp.apikey}")
	private String mailChimpApiKey;
	
	@Value("${activemq.broker-url}")
	private String brokerUrl;
	
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
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
	    activeMQConnectionFactory.setBrokerURL(brokerUrl);

	    return activeMQConnectionFactory;
	}
	
	@Bean
	public CachingConnectionFactory cachingConnectionFactory() {
		return new CachingConnectionFactory(activeMQConnectionFactory());
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
		return new JmsTemplate(cachingConnectionFactory());
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