package com.myclass.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.LoginDto;
import com.myclass.dto.UserLoginDto;
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
			// tạo token đăng nhập
			String token = authService.login(dto);

			// lấy ra thông tin user đăng nhập
			UserLoginDto userLoginDto = userService.getUserLoginDtoByEmail(dto.getEmail());

			// thêm token vào
			userLoginDto.setToken(token);

			// gửi trả về thông tin user đăng nhập vào hệ thống và token
			return new ResponseEntity<Object>(userLoginDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
}
