package com.myclass.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.dto.AddVideoDto;
import com.myclass.dto.EditVideoDto;
import com.myclass.dto.VideoDto;
import com.myclass.entity.Video;
import com.myclass.repository.VideoRepository;
import com.myclass.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	private VideoRepository videoRepository;

	public VideoServiceImpl(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}

	public List<VideoDto> getAllWithCourse() {
		return videoRepository.getAllWithCourse();
	}

	public void add(AddVideoDto entity) {
		videoRepository
				.save(new Video(0, entity.getTitle(), entity.getUrl(), entity.getTimeCount(), entity.getCourseId()));
	}

	public EditVideoDto getVideoById(int id) {
		Video entity = videoRepository.findById(id).get();
		return new EditVideoDto(entity.getId(), entity.getTitle(), entity.getUrl(), entity.getTimeCount(),
				entity.getCourseId());
	}

	public void edit(EditVideoDto entity) {
		videoRepository.save(new Video(entity.getId(), entity.getTitle(), entity.getUrl(), entity.getTimeCount(),
				entity.getCourseId()));
	}

	public void deleteById(int id) {
		videoRepository.deleteById(id);
	}

}
