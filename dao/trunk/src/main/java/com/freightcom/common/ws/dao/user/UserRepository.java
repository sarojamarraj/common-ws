package com.freightcom.common.ws.dao.user;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.freightcom.common.model.user.User;

/**
 * @author Kiran
 *
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	User findByUserName(String username);
}