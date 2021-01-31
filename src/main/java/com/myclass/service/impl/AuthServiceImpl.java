package com.myclass.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.myclass.dto.LoginDto;
import com.myclass.service.AuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Transactional(rollbackOn = Exception.class)
public class AuthServiceImpl implements AuthService {
	private AuthenticationManager authenticationManager;

	@Value("${secret}")
	private String secretKey;

	public AuthServiceImpl(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public String login(LoginDto dto) {
		System.out.println(secretKey);
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		Date now = new Date();
		Date expiration = new Date(now.getTime() + 840000000L);

		String token = Jwts.builder().setSubject(dto.getEmail()).setIssuedAt(now).setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
		return token;
	}

}
