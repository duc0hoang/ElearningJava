package com.myclass.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Category;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Integer>{

//	@Query("SELECT c FROM Category c WHERE c.title = :title")
//	public Category findByTitle(@Param("title") String title);
	
	public Category findByTitle(String title);

	@Query("SELECT c FROM Category c WHERE c.icon = :icon")
	public Category findByIcon(@Param("icon") String icon);

}
