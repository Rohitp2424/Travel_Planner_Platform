package com.trvelplannerplatform.user.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	@Value("${jwt.expiration}")
	private long jwtExpirationInMillis; 
	
	private SecretKey getSigningKey() {
		
	    return Keys.hmacShaKeyFor(
	            secretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generateToken(User user) {
		log.info("generating Jwt token" +user);
		return Jwts.builder()
				.setSubject(user.getEmail())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMillis))
				.signWith(getSigningKey())
				.compact();

    }
	
	 public String extractUsername(String token) {
		 log.info("extracting username from the token" +token);
		 Claims claims = Jwts.parser()
			        .verifyWith(getSigningKey())
			        .build()
			        .parseSignedClaims(token)
			        .getPayload();
		 
		 String extractedUsername = claims.getSubject();
		 log.info(extractedUsername);
		return extractedUsername;

	    }

	 public Date extractExpiration(String token) {
		 log.info("extracting the expiration from the token" +token);
		 Claims claims = Jwts.parser()
		            .verifyWith(getSigningKey())
		            .build()
		            .parseSignedClaims(token)
		            .getPayload();
		 Date extractedExpiration = claims.getExpiration();
		 return extractedExpiration;

	    }

	 private boolean isTokenExpired(String token) {

		 	boolean tokenExpiration = extractExpiration(token).before(new Date());
		 	
		 	log.info("checking is token expired or not: {}", tokenExpiration);
		    return tokenExpiration;

		}
	 
	 public boolean isTokenValid(String token, UserDetails userDetails) {
		 String username = extractUsername(token);
		    
		 	boolean tokenValidation = username.equals(userDetails.getUsername())
		            && !isTokenExpired(token);
		 	
		 	log.info("Username from JWT      : {}", username);
		    log.info("Username from Database : {}", userDetails.getUsername());
		    log.info("Expired : {}", isTokenExpired(token));
		 	return tokenValidation;
	    }
}
