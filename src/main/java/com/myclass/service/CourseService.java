package com.myclass.service;

import java.util.List;

import com.myclass.dto.AddCourseDto;
import com.myclass.dto.CourseDto;
import com.myclass.dto.EditCourseDto;

public interface CourseService {

	List<CourseDto> getAllWithCategory();

	void add(AddCourseDto entity);

	void edit(EditCourseDto entity);

	void deleteById(int id);

	EditCourseDto getCourseById(int id);

	boolean findByTitle(String title);

}
