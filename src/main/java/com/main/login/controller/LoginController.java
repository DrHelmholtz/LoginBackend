package com.main.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.main.login.domain.User;
import com.main.login.helper.Hash;
import com.main.login.service.LoginService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

	private LoginService loginService;

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	/**
	 * Login User
	 * @param User
	 * @return ResponseEntity<String>
	 * @author Boldi
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> makeLogin(@RequestBody User user) {
		if(!loginService.existUser(user.getUsername())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();			
		}	
		User u_tmp=loginService.getUserByUsername(user.getUsername());
		if(u_tmp.getPassword().equals(Hash.toSHAHash(user.getPassword()))) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	/**
	 * Add new User
	 * @param user
	 * @return ResponseEntity<String>
	 * @author Boldi
	 */
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody User user) {
		if(loginService.existUser(user.getUsername())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();			
		}
		User u_tmp=loginService.addUser(user);
		if(u_tmp!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}

		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).build();
	}
}
