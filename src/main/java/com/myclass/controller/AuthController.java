package com.myclass.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.LoginDto;
import com.myclass.dto.SignUpDto;
import com.myclass.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("api/auth")
public class AuthController {
	private AuthenticationManager authenticationManager;
	private UserService userService;

	@Value("${token.secretKey}")
	private String secretKey;

	public AuthController(AuthenticationManager authenticationManager, UserService userService) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
	}

	@PostMapping("login")
	public Object post(@Valid @RequestBody LoginDto dto) {
		Authentication authentication = null;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			Date now = new Date();
			Date expiration = new Date(now.getTime() + 84000000L);

			String token = Jwts.builder().setSubject(dto.getEmail()).setIssuedAt(now).setExpiration(expiration)
					.signWith(SignatureAlgorithm.HS512, secretKey).compact();

			return new ResponseEntity<Object>(token, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("signup")
	public Object post(@Valid @RequestBody SignUpDto dto) {
		try {
			userService.signUp(dto);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
}
