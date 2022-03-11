package com.market.authenticator.service;

import com.market.authenticator.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtSecurityTokenGeneratorImplementation implements SecurityTokenGenerator{
	
	@Override
	public Map<String, String> generateToken(User user) {
		String jwtToken = "";
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		jwtToken = Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date()).signWith( key ).compact();
		Map<String, String> map = new HashMap<>();
		map.put("token", jwtToken);
		map.put("message", "User logged in Successfully");
		return map;
	}



}
