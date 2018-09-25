package com.freightcom.common.ws.service.user.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.freightcom.common.dto.mail.MailRecipient;
import com.freightcom.common.dto.mail.MailRequest;
import com.freightcom.common.dto.mail.SendMail;
import com.freightcom.common.model.user.User;
import com.freightcom.common.model.user.VerificationToken;
import com.freightcom.common.ws.dao.user.UserRepository;
import com.freightcom.common.ws.dao.user.VerificationTokenRepository;
import com.freightcom.common.ws.service.user.UserService;

@PropertySources({
	@PropertySource(value="classpath:queue.properties"),
	@PropertySource(value="classpath:queue-overrides.properties",ignoreResourceNotFound=true)
})
@Service
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService {
	private final transient Log log = LogFactory.getLog(getClass());

	//@Value("${validation.email.expiry}")
	private int expiryTimeInMinutes = 2880; 
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	@Value("${mail.queue}")
	private String mailQueue;
	
	@Value("${mailchimp.mandrill.validate.email.template}")
	private String validateEmailTemplate;
	
	@Value("${validation.email.subject}")
	private String validateEmailSubject;
	
	@Value("${validation.email.from.name}")
	private String validateEmailfromName;
	
	@Value("${validation.email.from.email}")
	private String validateEmailfromEmail;
	
	@Value("${validation.returnurl}")
	private String validateReturnURL;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void sendValidationEmail(String userName) {
		User user = userRepository.findByUserName(userName);
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationToken.setExpiryDate(expiryTimeInMinutes);
		verificationTokenRepository.save(verificationToken);
		
		MailRequest mailRequest = new MailRequest();
		SendMail sendMail = new SendMail();
		MailRecipient from = new MailRecipient(validateEmailfromEmail, validateEmailfromName);
		sendMail.setFrom(from);
		sendMail.setSubject(validateEmailSubject);
		MailRecipient to = new MailRecipient(user.getCustomerEmail());
		sendMail.addToRecipient(to);
		sendMail.setTemplate(validateEmailTemplate);
		sendMail.addTemplateValue("returnurl", validateReturnURL+"?token="+token);
		jmsTemplate.convertAndSend(mailQueue, mailRequest);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void confirmValidationEmail(String token) {
		VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
		if(verificationToken == null) {
			throw new ValidationException("Invalid Token");
		}
		
		User user = verificationToken.getUser();
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime expiryDate = LocalDateTime.fromDateFields(verificationToken.getExpiryDate());
		if(now.isAfter(expiryDate)) {
			throw new ValidationException("Expired Token");
		}
		
		user.setEnabled(true);
		userRepository.save(user);
	}
}