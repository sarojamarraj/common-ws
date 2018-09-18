package com.freightcom.common.ws.webservice.messaging;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/mail")
public class MailController {
	@RequestMapping(path="/send" , method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> test() {
		ResponseEntity<String> re = new ResponseEntity<String>("hello", HttpStatus.OK);
		return re;
	}
}