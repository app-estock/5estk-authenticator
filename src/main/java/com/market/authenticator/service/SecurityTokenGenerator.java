package com.market.authenticator.service;

import java.util.Map;

import com.market.authenticator.model.User;


public interface SecurityTokenGenerator {
	
	Map<String, String> generateToken(User user);
	}