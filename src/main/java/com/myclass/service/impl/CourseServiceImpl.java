package com.myclass.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.dto.AddCourseDto;
import com.myclass.dto.CourseDto;
import com.myclass.dto.EditCourseDto;
import com.myclass.entity.Course;
import com.myclass.repository.CourseRepository;
import com.myclass.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{
	private CourseRepository courseRepository;
	
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<CourseDto> getAllWithCategory() {
		return courseRepository.getAllWithCategory();
	}
	
	public void add(AddCourseDto entity) {
		courseRepository.save(new Course(0, entity.getTitle(), entity.getImage(),entity.getLeturesCount(),
				0,0,entity.getPrice(),entity.getDiscount(),entity.getPromotionPrice(),entity.getDescription(),
				entity.getContent(), entity.getCategoryId(),new Date()));
	}
	
	public void deleteById(int id) {
		courseRepository.deleteById(id);
	}
	
	public void edit(EditCourseDto entity) {
		courseRepository.save(new Course(entity.getId(), entity.getTitle(), entity.getImage(),entity.getLeturesCount(),
				0,0,entity.getPrice(),entity.getDiscount(),entity.getPromotionPrice(),entity.getDescription(),
				entity.getContent(), entity.getCategoryId(),new Date()));
	}

	public EditCourseDto getCourseById(int id) {
		Course entity = courseRepository.findById(id).get();
		return new EditCourseDto(entity.getId(), entity.getTitle(), entity.getImage(),entity.getLeturesCount(),
				entity.getPrice(),entity.getDiscount(),entity.getPromotionPrice(),entity.getDescription(),
				entity.getContent(), entity.getCategoryId());
	}

	public boolean findByTitle(String title) {
		if(courseRepository.findByTitle(title) == null)
			return false;
		return true;
	}

}
