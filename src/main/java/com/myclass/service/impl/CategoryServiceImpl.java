package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.myclass.dto.AddCategoryDto;
import com.myclass.dto.CategoryDto;
import com.myclass.entity.Category;
import com.myclass.repository.CategoryRepository;
import com.myclass.service.CategoryService;

@Service
@Transactional(rollbackOn = Exception.class)
public class CategoryServiceImpl implements CategoryService {
	private CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public void deleteById(int id) {
		// xóa category theo id dưới database
		categoryRepository.deleteById(id);
	}

	public List<CategoryDto> getAllCategory() {
		// chuyển entity qua dto
		List<Category> categoryList = categoryRepository.findAll();
		List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
		for (Category category : categoryList) {
			CategoryDto categoryDto = new CategoryDto(category.getId(), category.getIcon(), category.getTitle());
			categoryDtoList.add(categoryDto);
		}
		return categoryDtoList;
	}

	public CategoryDto getCategoryById(int id) {
		// chuyển entity qua dto
		Category category = categoryRepository.findById(id).get();
		return new CategoryDto(category.getId(), category.getIcon(), category.getTitle());
	}

	public void add(AddCategoryDto entity) {
		// thêm category vào database
		categoryRepository.save(new Category(0, entity.getTitle(), entity.getIcon()));
	}

	public void edit(CategoryDto entity) {
		// sửa category dưới database
		categoryRepository.save(new Category(entity.getId(), entity.getTitle(), entity.getIcon()));
	}
	
	public boolean checkExistById(int id) {
		// kiểm tra id có tồn tại dưới database chưa
		return categoryRepository.findById(id).isPresent();
	}

	public boolean checkExistByTitle(String title) {
		// kiểm tra category có tồn tại dưới database chưa
		if (categoryRepository.findByTitle(title) == null)
			return false;
		return true;
	}

}
