package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.dto.AddCategoryDto;
import com.myclass.dto.CategoryDto;
import com.myclass.entity.Category;
import com.myclass.repository.CategoryRepository;
import com.myclass.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public void deleteById(int id) {
		categoryRepository.deleteById(id);
	}

	public List<CategoryDto> getAllCategory() {
		List<Category> categoryList = categoryRepository.findAll();
		List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
		for (Category category : categoryList) {
			CategoryDto categoryDto = new CategoryDto(category.getId(), category.getIcon(), category.getTitle());
			categoryDtoList.add(categoryDto);
		}
		return categoryDtoList;
	}

	public CategoryDto getCategoryById(int id) {
		Category category = categoryRepository.findById(id).get();
		return new CategoryDto(category.getId(), category.getIcon(), category.getTitle());
	}

	public void add(AddCategoryDto entity) {
		categoryRepository.save(new Category(0, entity.getTitle(), entity.getIcon()));
	}

	public void edit(CategoryDto entity) {
		categoryRepository.save(new Category(entity.getId(), entity.getTitle(), entity.getIcon()));
	}

	public boolean findByTitle(String title) {
		if(categoryRepository.findByTitle(title) == null)
			return false;
		return true;
	}

	public boolean findByIcon(String icon) {
		if(categoryRepository.findByIcon(icon) == null)
			return false;
		return true;
	}

}
