package com.uxpsystems.assignment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.dao.UserRepository;

@Service
@CacheConfig(cacheNames = "HAZELCAST_REQUEST_CONFIG")
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	HazelcastInstance hazelcastInstance;
	
	/**
	 * This method returns all users from database.
	 * @return
	 */
	@Cacheable(value="allusers")
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll()
		.forEach(users::add);
		return users;
	}
	
	/**
	 * This method takes id as parameter and returns the User object for that id.
	 * @param id
	 * @return
	 */
	@Cacheable(value="allusers", key="#id")
	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}
	
	/**
	 * This method takes a user object and saves it into db. 
	 * @param user
	 */
	@CachePut(value="allusers", key="#user.id")
	public void addUser(User user) {
		userRepository.save(user);
		
	}

	/**
	 * This method takes user and id as parameters and updates the user in db.
	 * @param id
	 * @param user
	 */
	@CachePut(value="allusers", key="#id")
	public void updateUser(Long id, User user) {
		userRepository.save(user);
		
	}

	/**
	 * This method takes id as parameter and deletes that user pertaining to that id in from db.
	 * @param id
	 */
	@CacheEvict(value="allusers", key="#id")
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
		
	}
}
