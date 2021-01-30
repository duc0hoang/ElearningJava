package com.myclass.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.myclass.dto.AddVideoDto;
import com.myclass.dto.EditVideoDto;
import com.myclass.dto.VideoDto;
import com.myclass.entity.Video;

public interface VideoService {
	List<VideoDto> getAllWithCourse();

	void add(AddVideoDto entity);

	EditVideoDto getVideoById(int id);

	void edit(EditVideoDto entity);

	void deleteById(int id);

	boolean checkExistByTitle(String title);

	boolean checkExistById(int id);

	List<VideoDto> getMenuVideoByCourseId(int id);

	boolean checkProperty(String orderBy);

	Page<Video> findAllPaging(String orderBy, int i, int pageSize, boolean b);

}
