package com.freightcom.common.ws.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freightcom.common.model.user.User;
import com.freightcom.common.model.user.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
	VerificationToken findByToken(String token);
	VerificationToken findByUser(User user);
}