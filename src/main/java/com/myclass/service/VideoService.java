package com.myclass.service;

import java.util.List;

import com.myclass.dto.AddVideoDto;
import com.myclass.dto.EditVideoDto;
import com.myclass.dto.VideoDto;

public interface VideoService {
	List<VideoDto> getAllWithCourse();

	void add(AddVideoDto entity);

	EditVideoDto getVideoById(int id);

	void edit(EditVideoDto entity);

	void deleteById(int id);

}
