package com.market.authenticator.controller;

import java.util.Map;

import com.market.authenticator.model.User;
import com.market.authenticator.service.SecurityTokenGenerator;
import com.market.authenticator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestController
@CrossOrigin(origins = "http://65.2.124.176:4200", maxAge = 3600)
@RequestMapping("/api/v1.0/market/user")
public class UserController {

	@Autowired
	private UserService userService;
    
    @Autowired
   	private SecurityTokenGenerator tokenGenerator;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			userService.saveUser(user);
			return new ResponseEntity<String>("User registered successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User loginDetail) {

		try {
	
			String userId = loginDetail.getUserId();
			String password = loginDetail.getPassword();

			if (userId == null || password == null) {
				throw new Exception("Username or password cannot be empty");

			}

			User user = userService.findByUserIdAndPassword(userId, password);
			if (user == null) {
				throw new Exception("User with given Id does not exists");
			}
			String pwd = user.getPassword();
			if (!password.equals(pwd)) {
				throw new Exception("Invalid login credential, Please check username and password ");
			}

			Map<String, String> map = tokenGenerator.generateToken(user);
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
		}

	}

}