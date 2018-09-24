package com.freightcom.common.ws.service.user;

public interface UserService {
	public void sendValidationEmail(String userName);
	public void confirmValidationEmail(String token);
}