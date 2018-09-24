package com.freightcom.common.ws.webservice.user;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.freightcom.common.ws.service.user.UserService;

@RestController
@RequestMapping(path="/api/user")
public class UserController {
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path="/sendvalidation/{userName}" , method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendValidation(@PathVariable String userName) {
		ResponseEntity<?> re = null;
		try {
			userService.sendValidationEmail(userName);
			re = new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<String>("Error sending Validation Email",HttpStatus.INTERNAL_SERVER_ERROR);
			logger.error("Error Registrating",e);
		}
		return re;
	}
	
	@RequestMapping(path="/validate/token" , method=RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> validate(@PathVariable String token) {
		ResponseEntity<?> re = null;
		try {
			userService.confirmValidationEmail(token);
			re = new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			re = new ResponseEntity<String>("Error Validating token",HttpStatus.INTERNAL_SERVER_ERROR);
			logger.error("Error Validating user",e);
		}
		return re;
	}
}