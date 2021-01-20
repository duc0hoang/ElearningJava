package com.myclass.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.dto.AddTargetDto;
import com.myclass.dto.EditTargetDto;
import com.myclass.dto.TargetDto;
import com.myclass.entity.Target;
import com.myclass.repository.TargetRepository;
import com.myclass.service.TargetService;

@Service
public class TargetServiceImpl implements TargetService{
	private TargetRepository targetRepository;
	
	public TargetServiceImpl(TargetRepository targetRepository) {
		this.targetRepository = targetRepository;
	}

	public List<TargetDto> getAllWithCourse() {
		return targetRepository.getAllWithCourse();
	}
	
	public void add(AddTargetDto entity) {
		targetRepository.save(new Target(0,entity.getTitle(), entity.getCourseId()));
	}

	public EditTargetDto getTargetById(int id) {
		Target entity = targetRepository.findById(id).get();
		return new EditTargetDto(entity.getId(),entity.getTitle(), entity.getCourseId());
	}

	public void deleteById(int id) {
		targetRepository.deleteById(id);
	}

	public void edit(EditTargetDto entity) {
		targetRepository.save(new Target(entity.getId(),entity.getTitle(), entity.getCourseId()));
	}

}
