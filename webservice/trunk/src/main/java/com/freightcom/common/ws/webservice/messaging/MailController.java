package com.freightcom.common.ws.webservice.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author rnarine
 *
 */
@RestController
@RequestMapping(path="/api/mail")
public class MailController {
	@Value("${mail.queue}")
	private String mailQueue;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@RequestMapping(path="/send" , method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> send(@RequestBody String mailRequest) {
		ResponseEntity<?> re = null;
		try {
			jmsTemplate.convertAndSend(mailQueue, mailRequest);
			re = new ResponseEntity<Void>(HttpStatus.OK);
		} catch (JmsException e) {
			re = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return re;
	}
}