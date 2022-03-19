package com.market.authenticator.service;

import com.market.authenticator.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.*;

@Service
public class JwtSecurityTokenGeneratorImplementation implements SecurityTokenGenerator{
	
	@Override
	public Map<String, String> generateToken(User user) {
		String jwtToken = "";
		//SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		String secret = "1231231231412aisdjasndskfniwn21312312312143423sdasdrcwqeqwdsadd23123";

		Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName());
		jwtToken = Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date()).signWith(hmacKey).compact();

		Map<String, String> map = new HashMap<>();
		map.put("token", jwtToken);
		map.put("message", "User logged in Successfully");
		return map;
	}



}
