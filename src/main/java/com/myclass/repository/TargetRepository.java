package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.dto.TargetDto;
import com.myclass.entity.Target;

@Repository
public interface TargetRepository extends BaseRepository<Target, Integer>{
	
	@Query("SELECT new com.myclass.dto.TargetDto(t.id, t.title, c.title) FROM Target t LEFT JOIN Course c ON t.courseId = c.id")
	public List<TargetDto> getAllWithCourse();

	public Target findByTitle(String title);
}
