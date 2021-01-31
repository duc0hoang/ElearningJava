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

import com.myclass.dto.AddCourseDto;
import com.myclass.dto.EditCourseDto;
import com.myclass.service.CategoryService;
import com.myclass.service.CourseService;

@RestController
@RequestMapping("/api/admin/course")
public class AdminCourseController {
	private CourseService courseService;
	private CategoryService categoryService;

	@Value("${message.title}")
	private String titleIsExist;

	@Value("${message.category}")
	private String categoryIsNotExist;

	@Value("${message.id}")
	private String idIsNotExist;

	public AdminCourseController(CourseService courseService, CategoryService categoryService) {
		this.courseService = courseService;
		this.categoryService = categoryService;
	}

	// lấy danh sách course
	@GetMapping("")
	public Object get() {
		try {
			// trả về danh sách course
			return new ResponseEntity<Object>(courseService.getAllWithCategory(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	// thêm vào 1 course mới
	@PostMapping("")
	public Object post(@Valid @RequestBody AddCourseDto entity) {
		try {
			// check xem title có bị trùng không
			if (courseService.checkExistByTitle(entity.getTitle()))
				return new ResponseEntity<Object>(titleIsExist, HttpStatus.BAD_REQUEST);

			// check xem categoryId có tồn tại chưa
			if (!categoryService.checkExistById(entity.getCategoryId()))
				return new ResponseEntity<Object>(categoryIsNotExist, HttpStatus.BAD_REQUEST);

			// thêm course mới vào database
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
			// check xem courseId có tồn tại chưa
			if (!courseService.checkExistById(id))
				return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

			// trả về course theo id gửi lên
			return new ResponseEntity<Object>(courseService.getCourseById(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("{id}")
	public Object put(@Valid @RequestBody EditCourseDto entity, @PathVariable int id) {
		try {
			// check xem id gửi lên và id trong course có trùng hay không
			if (id != entity.getId())
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

			// check xem courseId có tồn tại chưa
			if (!courseService.checkExistById(entity.getId()))
				return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

			// check xem title có bị trùng không
			if (courseService.checkExistByTitle(entity.getTitle()))
				return new ResponseEntity<Object>("Title is exist", HttpStatus.BAD_REQUEST);

			// check xem categoryId có tồn tại chưa
			if (!categoryService.checkExistById(entity.getCategoryId()))
				return new ResponseEntity<Object>(categoryIsNotExist, HttpStatus.BAD_REQUEST);

			// sửa course trong database
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
			// check xem courseId có tồn tại chưa
			if (!courseService.checkExistById(id))
				return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

			// xóa course theo id gửi lên dưới database
			courseService.deleteById(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("getImage/{id}")
	public Object getImage(@PathVariable int id) {
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);	
	}
	
	@PostMapping("addImage/{id}")
	public Object addImage(@PathVariable int id) {
		
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);	
	}
	
	@PutMapping("editImage/{id}")
	public Object editImage(@PathVariable int id) {
		
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);	
	}
	
	@DeleteMapping("deleteImage/{id}")
	public Object deleteImage(@PathVariable int id) {
		
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);	
	}
}
