package com.uxpsystems.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.service.UserService;

import io.swagger.annotations.ApiOperation;

/**
 * @author ssusarla
 *
 */
@RestController
@RequestMapping ("/Assignment")
@EnableCaching
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * This method returns all users.
	 * @return List<User>
	 */
	@GetMapping("/users")
	@ApiOperation(value = "Get all users!")

	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * This method takes id as parameter and returns the User object for that id.
	 * @param id
	 * @return User
	 */
	@GetMapping("/users/{id}")
	@ApiOperation(value = "Find user by id")
	public User getUser(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	/**
	 * This method takes a user object and saves it into db. 
	 * @param user
	 */
	@PostMapping("/users")
	@ApiOperation(value = "Add new User!")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
		
	}
	
	/**
	 * This method takes user and id as parameters and updates the user in db.
	 * @param user
	 * @param id
	 */
	@PutMapping("/users/{id}")
	@ApiOperation(value = "Update an User!")
	public void updateUser(@RequestBody User user, @PathVariable Long id) {
		userService.updateUser(id, user);
		
	}
	
	/**
	 * This method takes id as parameter and deletes that user pertaining to that id in from db.
	 * @param id
	 */
	@DeleteMapping("/users/{id}")
	@ApiOperation(value = "Delete an User!")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		
	}

}
