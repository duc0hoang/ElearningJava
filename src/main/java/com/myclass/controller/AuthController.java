package com.myclass.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.LoginDto;
import com.myclass.dto.SignUpDto;
import com.myclass.service.AuthService;
import com.myclass.service.UserService;

@RestController
@RequestMapping("api/auth")
public class AuthController {
	private AuthService authService;
	private UserService userService;

	

	public AuthController(AuthService authService, UserService userService) {
		this.authService = authService;
		this.userService = userService;
	}

	@PostMapping("login")
	public Object post(@Valid @RequestBody LoginDto dto) {
		try {
			
			String token = authService.login(dto);

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
