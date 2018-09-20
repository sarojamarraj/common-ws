package com.freightcom.common.ws.service.mail;

import java.io.IOException;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;

/**
 * Service for email
 * @author rnarine
 * @version 1.0
 * @since 1.0
 */
public interface MailService {
	/**
	 * 
	 * @param sendMail
	 * @throws MandrillApiError
	 * @throws IOException
	 */
	public void sendSingleEmail(SendMail sendMail) throws MandrillApiError, IOException;
	
	/**
	 * 
	 * @param addToList
	 * @throws Exception
	 */
	public void addToList(AddToList addToList) throws Exception;
}