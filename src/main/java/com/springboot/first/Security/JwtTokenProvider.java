package com.springboot.first.Security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.springboot.first.exception.APIException;
import com.springboot.first.model.Admin;
import com.springboot.first.model.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component

public class JwtTokenProvider {

	
	public String generateAdminToken(Admin admin) {
		String userName = admin.getUserName();
		Date currentDate = new Date();
		Date expiryDate = new Date(currentDate.getTime() + 3600000);

		String token = Jwts.builder().setSubject(userName).setIssuedAt(currentDate).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, "JWTSecretKey").compact();
		return token;
	}
	
	public String generateUsersToken(Users users) {
		String userName = users.getUserName();
		Date currentDate = new Date();
		Date expiryDate = new Date(currentDate.getTime() + 3600000);

		String token = Jwts.builder().setSubject(userName).setIssuedAt(currentDate).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, "JWTSecretKey").compact();
		return token;
	}

	public String getUserNameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey("JWTSecretKey").parseClaimsJws(token).getBody();
		return claims.getSubject();
		
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey("JWTSecretKey").parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			throw new APIException("Token issue:"+e.getMessage());
		}
	}
}
