package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.dto.VideoDto;
import com.myclass.entity.Video;

@Repository
public interface VideoRepository extends BaseRepository<Video, Integer>{
	
	@Query("SELECT new com.myclass.dto.VideoDto(v.id, v.title, v.url, v.timeCount, c.title) FROM Video v LEFT JOIN Course c ON v.courseId = c.id")
	public List<VideoDto> getAllWithCourse();

	public Video findByTitle(String title);

	@Query("SELECT new com.myclass.dto.VideoDto(v.id, v.title, v.url, v.timeCount, c.title) FROM Video v LEFT JOIN Course c ON v.courseId = c.id WHERE c.id = :id")
	public List<VideoDto> getMenuVideoByCourseId(int id);

}
