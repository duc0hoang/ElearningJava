package com.myclass.admin.controller;

import javax.validation.Valid;

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

import com.myclass.dto.AddCategoryDto;
import com.myclass.dto.CategoryDto;
import com.myclass.service.CategoryService;

@RestController
@RequestMapping("/api/admin/category")
public class CategoryController {
	private CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping("")
	public Object get() {
		try {
			return new ResponseEntity<Object>(categoryService.getAllCategory(),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("{id}")
	public Object get(@PathVariable int id) {
		try {
			return new ResponseEntity<Object>(categoryService.getCategoryById(id),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("")
	public Object post(@Valid @RequestBody AddCategoryDto entity) {
		try {
			if(categoryService.findByTitle(entity.getTitle()))
				return new ResponseEntity<Object>("Title is exist",HttpStatus.BAD_REQUEST);
			
			if(categoryService.findByIcon(entity.getIcon()))
				return new ResponseEntity<Object>("Title is exist",HttpStatus.BAD_REQUEST);
			
			categoryService.add(entity);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("{id}")
	public Object put(@RequestBody CategoryDto entity, @PathVariable int id) {
		try {
			if (id != entity.getId())
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			
			if(categoryService.findByTitle(entity.getTitle()))
				return new ResponseEntity<Object>("Title is exist",HttpStatus.BAD_REQUEST);
			
			if(categoryService.findByIcon(entity.getIcon()))
				return new ResponseEntity<Object>("Title is exist",HttpStatus.BAD_REQUEST);
			
			categoryService.edit(entity);
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
			categoryService.deleteById(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
}
