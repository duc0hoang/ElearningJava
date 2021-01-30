package com.myclass.admin.controller;

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

import com.myclass.dto.AddTargetDto;
import com.myclass.dto.EditTargetDto;
import com.myclass.service.CourseService;
import com.myclass.service.TargetService;

@RestController
@RequestMapping("/api/admin/target")
public class AdminTargetController {
	private TargetService targetService;
	private CourseService courseService;

	@Value("${message.id}")
	private String idIsNotExist;

	@Value("${message.title}")
	private String titleIsExist;
	
	@Value("${message.course}")
	private String courseIsNotExist;

	public AdminTargetController(TargetService targetService, CourseService courseService) {
		this.targetService = targetService;
		this.courseService = courseService;
	}

	@GetMapping("")
	public Object get() {
		try {
			// trả về danh sách target có course name
			return new ResponseEntity<Object>(targetService.getAllWithCourse(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("")
	public Object post(@Valid @RequestBody AddTargetDto entity) {
		try {
			// check xem target title có bị trùng hay không
			if (targetService.checkExistByTitle(entity.getTitle()))
				return new ResponseEntity<Object>(titleIsExist, HttpStatus.BAD_REQUEST);

			// check xem course id có tồn tại hay không
			if (!courseService.checkExistById(entity.getCourseId()))
				return new ResponseEntity<Object>(courseIsNotExist, HttpStatus.BAD_REQUEST);

			// thêm target mới vào database
			targetService.add(entity);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("{id}")
	public Object get(@PathVariable int id) {
		try {
			// check xem target id có tồn tại hay không
			if (!targetService.checkExistById(id))
				return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

			// trả về target theo id gửi lên
			return new ResponseEntity<Object>(targetService.getTargetById(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("{id}")
	public Object put(@RequestBody EditTargetDto entity, @PathVariable int id) {
		try {
			// check xem id gửi lên và id trong target có giống nhau hay không
			if (id != entity.getId())
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

			// check xem target id có tồn tại hay không
			if (!targetService.checkExistById(id))
				return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

			// check xem target title có bị trùng hay không
			if (targetService.checkExistByTitle(entity.getTitle()))
				return new ResponseEntity<Object>(titleIsExist, HttpStatus.BAD_REQUEST);

			// check xem course id có tồn tại hay không
			if (!courseService.checkExistById(entity.getCourseId()))
				return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

			// sửa target dưới database
			targetService.edit(entity);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("{id}")
	public Object delete(@PathVariable int id) {
		try {
			// check xem target id có tồn tại hay không
			if (!targetService.checkExistById(id))
				return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

			// xóa target theo id gửi lên dưới database
			targetService.deleteById(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
}
