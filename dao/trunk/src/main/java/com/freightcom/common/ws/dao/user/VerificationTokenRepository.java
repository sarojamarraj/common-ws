package com.freightcom.common.ws.dao.user;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.freightcom.common.model.user.User;
import com.freightcom.common.model.user.VerificationToken;

public interface VerificationTokenRepository extends PagingAndSortingRepository<VerificationToken, Long> {
	VerificationToken findByToken(String token);
	VerificationToken findByUser(User user);
}