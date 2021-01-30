package com.myclass.service;

import java.util.List;

import com.myclass.dto.AddCategoryDto;
import com.myclass.dto.CategoryDto;

public interface CategoryService {

	void deleteById(int id);

	List<CategoryDto> getAllCategory();

	CategoryDto getCategoryById(int id);

	void add(AddCategoryDto entity);

	void edit(CategoryDto entity);

	boolean checkExistById(int id);

	boolean checkExistByTitle(String title);

}
