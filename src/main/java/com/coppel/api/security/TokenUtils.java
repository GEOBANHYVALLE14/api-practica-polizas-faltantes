package com.coppel.api.security;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.coppel.api.controller.impl.PolizasControllerImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenUtils {
	private static Logger logger = LoggerFactory.getLogger(PolizasControllerImpl.class);

	private final static String ACCESS_TOKEN_SECRET = "r1gqW4sLYCH6ra4geyuwmOx9G5px12fI";
	// private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;// 30
	// dias

	// private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 120L;

	// @Value("${token.expiration.date}")
	public static long EXPIRATION_DATE = 28800000;// 8h

	public static String createToken(String name, String username) {
		long expirationTime = EXPIRATION_DATE * 1000;
		Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
		Map<String, Object> extra = new HashMap<>();
		extra.put("nombre", name);
		logger.info("createToken()");
		return Jwts.builder().setSubject(username).setExpiration(expirationDate).addClaims(extra)
				.signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes())).compact();

	}

	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			Claims claims = Jwts.parserBuilder().setSigningKey(ACCESS_TOKEN_SECRET.getBytes()).build()
					.parseClaimsJws(token).getBody();
			
			String username = claims.getSubject();
			logger.info("getAuthentication()");

			return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());

		} catch (JwtException e) {
			return null;
		}
	}
}
