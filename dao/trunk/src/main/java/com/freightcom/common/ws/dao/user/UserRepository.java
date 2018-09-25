package com.freightcom.common.ws.dao.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freightcom.common.model.user.User;

/**
 * @author Kiran
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String username);
}