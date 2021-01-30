package com.myclass.repository;

import org.springframework.stereotype.Repository;

import com.myclass.entity.Category;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Integer>{

	public Category findByTitle(String title);

}
