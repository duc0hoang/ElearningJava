package com.myclass.service;

import java.util.List;

import com.myclass.dto.AddTargetDto;
import com.myclass.dto.EditTargetDto;
import com.myclass.dto.TargetDto;

public interface TargetService {

	List<TargetDto> getAllWithCourse();

	void add(AddTargetDto entity);

	EditTargetDto getTargetById(int id);

	void deleteById(int id);

	void edit(EditTargetDto entity);

	boolean checkExistByTitle(String title);

	boolean checkExistById(int id);

	List<TargetDto> getMenuTargetByCourseId(int id);

}
