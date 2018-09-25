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

@PropertySources({
	@PropertySource(value="classpath:queue.properties"),
	@PropertySource(value="classpath:queue-overrides.properties",ignoreResourceNotFound=true)
})
@Configuration
@ComponentScan({ "com.freightcom.common.ws.service" })
public class ServiceConfig {
	@Value("${activemq.broker-url}")
	private String brokerUrl;
	
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
}