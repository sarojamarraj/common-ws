package com.freightcom.common.ws.service.mail;

import java.io.IOException;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;

/**
 * 
 * @author rnarine
 *
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