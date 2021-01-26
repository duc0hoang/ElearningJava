package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myclass.dto.CourseDto;
import com.myclass.entity.Course;

@Repository
public interface CourseRepository extends BaseRepository<Course, Integer>{
	
	@Query("SELECT new com.myclass.dto.CourseDto(c.id, c.title, c.image, c.leturesCount, c.hourCount, c.viewCount, c.price, c.discount, c.promotionPrice, c.description, c.content, ct.title, c.lastUpdate) FROM Course c LEFT JOIN Category ct ON c.categoryId = ct.id")
	List<CourseDto> getAllWithCategory();

	@Query("SELECT c FROM Course c WHERE c.title = :title")
	Course findByTitle(@Param("title") String title);

	List<Course> findAllByCategoryId(int id);

}
