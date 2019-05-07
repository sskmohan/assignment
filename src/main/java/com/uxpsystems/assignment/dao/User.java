package com.uxpsystems.assignment.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.cache.annotation.EnableCaching;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel(description="All details about the User. ")
@EnableCaching
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1149574624041491395L;
	@Id
	@Column (name = "id")
	private Long id;
	@Column (name = "username")
	private String userName;
	@Column (name = "password")
	private String password;
	@Column (name = "status")
	private String status;
	
	
	
	public User() {

	}

	public User(Long id, String userName, String password, String status) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.status = status;
	}
	
	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return passwrod
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return "Id: " + this.id + "', Name: '" + this.userName + "', Password: " + this.password + "', Status: '" + this.status + "'";
	}
	
}
