package com.myclass.admin.controller;

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

import com.myclass.dto.AddCourseDto;
import com.myclass.dto.EditCourseDto;
import com.myclass.service.CourseService;

@RestController
@RequestMapping("/api/admin/course")
public class AdminCourseController {
	private CourseService courseService;
	
	public AdminCourseController(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@GetMapping("")
	public Object get() {
		try {
			return new ResponseEntity<Object>(courseService.getAllWithCategory(),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("")
	public Object post(@RequestBody AddCourseDto entity) {
		try {
			if(courseService.findByTitle(entity.getTitle()))
				return new ResponseEntity<Object>("Title is exist",HttpStatus.BAD_REQUEST);
			
			courseService.add(entity);
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
			return new ResponseEntity<Object>(courseService.getCourseById(id),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("{id}")
	public Object put(@RequestBody EditCourseDto entity, @PathVariable int id) {
		try {
			if (id != entity.getId())
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			
			if(courseService.findByTitle(entity.getTitle()))
				return new ResponseEntity<Object>("Title is exist",HttpStatus.BAD_REQUEST);
			
			courseService.edit(entity);
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
			courseService.deleteById(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
}
