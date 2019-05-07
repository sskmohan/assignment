package com.uxpsystems.assignment.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {

	/**
	 * This method takes userName as parameter and returns User object for that name.
	 * @param userName
	 * @return
	 */
	public User findByUserName(String userName);

}
