package com.myclass.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.AddUserCourseDto;
import com.myclass.dto.PasswordDto;
import com.myclass.dto.SignUpDto;
import com.myclass.service.CourseService;
import com.myclass.service.UserCourseService;
import com.myclass.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
	private UserService userService;
	private CourseService courseService;
	private UserCourseService userCourseService;

	public UserController(UserService userService, CourseService courseService, UserCourseService userCourseService) {
		this.userService = userService;
		this.courseService = courseService;
		this.userCourseService = userCourseService;
	}

	@Value("${message.course}")
	private String courseIsNotExist;

	@Value("${message.email}")
	private String emailIsExist;

	@Value("${message.phone}")
	private String phoneIsExist;

	@Value("${message.username}")
	private String emailIsNotExist;
	
	@Value("${message.user}")
	private String userIsNotExist;

	@Value("${message.password}")
	private String passwrodIsNotCorrect;

	@PostMapping("signup")
	public Object signUp(@Valid @RequestBody SignUpDto dto) {
		try {
			// check xem email có tồn tại hay không
			if (userService.checkExistByEmail(dto.getEmail()))
				return new ResponseEntity<Object>(emailIsExist, HttpStatus.BAD_REQUEST);

			// check xem sdt có tồn tại hay không
			if (userService.checkExistByPhone(dto.getPhone()))
				return new ResponseEntity<Object>(phoneIsExist, HttpStatus.BAD_REQUEST);

			// đăng ký tài khoản mới
			userService.signUp(dto);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("changePassword")
	public Object changePassword(@Valid @RequestBody PasswordDto dto) {
		try {
			// check xem email có tồn tại hay không
			if (!userService.checkExistByEmail(dto.getEmail()))
				return new ResponseEntity<Object>(emailIsNotExist, HttpStatus.BAD_REQUEST);

			// check password cũ gửi lên có đúng hay không
			if (userService.checkPassword(dto.getEmail(), dto.getOldPassword()))
				return new ResponseEntity<Object>(passwrodIsNotCorrect, HttpStatus.BAD_REQUEST);

			// thay đổi password trong database thành password mới
			userService.changePassword(dto.getEmail(), dto.getNewPassword());
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("addCourse")
	public Object userAddCourse(@Valid @RequestBody AddUserCourseDto dto) {
		try {
			// check xem email có tồn tại hay không
			if (!userService.checkExistById(dto.getUserId()))
				return new ResponseEntity<Object>(emailIsNotExist, HttpStatus.BAD_REQUEST);

			// check xem course id có tồn tại hay không
			if (!courseService.checkExistById(dto.getCourseId()))
				return new ResponseEntity<Object>(courseIsNotExist, HttpStatus.BAD_REQUEST);
			
			// check xem user này đã add course này chưa
			if (userCourseService.checkUserWithCourse(dto))
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			
			userCourseService.addCourse(dto);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("{id}")
	public Object getInfo() {
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("getAvatar/{id}")
	public Object getAvatar(@PathVariable int id) {
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);	
	}
	
	@PostMapping("addAvatar/{id}")
	public Object addAvatar(@PathVariable int id) {
		
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);	
	}
	
	@PutMapping("editAvatar/{id}")
	public Object editAvatar(@PathVariable int id) {
		
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);	
	}
	
	@DeleteMapping("deleteAvatar/{id}")
	public Object deleteAvatar(@PathVariable int id) {
		
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);	
	}
}
