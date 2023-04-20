package com.toyproject.bookmanagement.security.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.toyproject.bookmanagement.dto.auth.JwtTokenRespDto;
import com.toyproject.bookmanagement.security.PrincipalUserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	private final Key key;
	
	public JwtTokenProvider(@Value("${jwt.secretKey}") String secretKey) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	public JwtTokenRespDto createToken(Authentication authentication) {
		
		String authorities = authentication.getAuthorities().toString().replace(" ", "");
		authorities = authorities.substring(1, authorities.length() - 1);
		
		long now = (new Date()).getTime();
		Date tokenExpiresDate = new Date(now + (1000 * 60 * 60));
		
		PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal();
		
		String accessToken = Jwts.builder()
				.setSubject(userDetails.getEmail())
				.claim("userId", userDetails.getUserId())
				.claim("auth", authorities)
				.setExpiration(tokenExpiresDate)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
		return JwtTokenRespDto.builder()
				.grantType("Bearer")
				.accessToken(accessToken)
				.build();
	}

}
