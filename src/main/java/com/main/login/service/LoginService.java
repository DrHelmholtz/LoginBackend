package com.main.login.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.login.domain.User;
import com.main.login.helper.Hash;
import com.main.login.repository.LoginRepository;

@Service
public class LoginService {
	private LoginRepository loginRepo;

	@Autowired
	public void setLoginRepo(LoginRepository loginRepo) {
		this.loginRepo = loginRepo;
	}
	
	/**
	 * 
	 * @param user
	 * @return User newly created user
	 * @throws ConstraintViolationException
	 * @author Boldi
	 */
	public User addUser(User user) throws ConstraintViolationException{
		user.setPassword(Hash.toSHAHash(user.getPassword()));
		return loginRepo.save(user);
	}
	/**
	 * Check the username already exist
	 * @param username
	 * @return boolean true if exist
	 * @author Boldi
	 */
	public boolean existUser(String username){
		return loginRepo.existsByUsername(username);
	}
	
	/**
	 * Get the user from database
	 * @param username
	 * @return User with the username
	 * @author Boldi
	 */
	public User getUserByUsername(String username) {
		return loginRepo.findFirstByUsername(username);
	}
	
	

}
