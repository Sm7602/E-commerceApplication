package com.example.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	//S-4
	private static final String SECRET_KEY="46ntYtPyXxjM6BXmHbmNJRo9fIfDnxfnoYqibx5OWvX";//copy form  encrypted keygenerator

	// s-1 frist create return null
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);//after s -5 retrun this
	}
	
	//s- 5 cerate the method
	public <T> T extractClaim(String token,Function<Claims,T> clamisresolver) {
		final Claims claims=extractAllClaims(token);
		return clamisresolver.apply(claims);
	}
	
	public String generaTetoken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}
	
	@SuppressWarnings("deprecation")
	public String generateToken(
			Map<String,Object> extractClaims,
			UserDetails userDetails) {
		return Jwts
				.builder()
				.setClaims(extractClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
				.signWith(getSignInKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	public boolean istokenValid(String token,UserDetails userDetails) {
		final String username=extractUsername(token);
		return (username.equals(userDetails.getUsername()))&& !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiredToken(token).before(new Date());
	}

	private Date extractExpiredToken(String token) {
		return extractClaim(token,Claims::getExpiration);
	}

	//s-2 then cerate the method
	 private Claims extractAllClaims(String token) {
	        return Jwts.parser()
	                .verifyWith(getSignInKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	    }

	//s-3 cerate the method
	private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);//cerate the SECRET_KEY
		return Keys.hmacShaKeyFor(keyBytes);
	}
}


