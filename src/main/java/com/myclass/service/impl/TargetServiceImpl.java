package com.myclass.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.myclass.dto.AddTargetDto;
import com.myclass.dto.EditTargetDto;
import com.myclass.dto.TargetDto;
import com.myclass.entity.Target;
import com.myclass.repository.TargetRepository;
import com.myclass.service.TargetService;

@Service
@Transactional(rollbackOn = Exception.class)
public class TargetServiceImpl implements TargetService {
	private TargetRepository targetRepository;

	public TargetServiceImpl(TargetRepository targetRepository) {
		this.targetRepository = targetRepository;
	}

	public List<TargetDto> getAllWithCourse() {
		// trả về danh sách target có course
		return targetRepository.getAllWithCourse();
	}

	public void add(AddTargetDto entity) {
		// thêm mới target
		targetRepository.save(new Target(0, entity.getTitle(), entity.getCourseId()));
	}

	public EditTargetDto getTargetById(int id) {
		// chuyển entity sang dto
		Target entity = targetRepository.findById(id).get();
		return new EditTargetDto(entity.getId(), entity.getTitle(), entity.getCourseId());
	}

	public void deleteById(int id) {
		// xóa target
		targetRepository.deleteById(id);
	}

	public void edit(EditTargetDto entity) {
		// sửa target
		targetRepository.save(new Target(entity.getId(), entity.getTitle(), entity.getCourseId()));
	}

	public boolean checkExistByTitle(String title) {
		// kiểm tra xem target title có tồn tại dưới database chưa
		if(targetRepository.findByTitle(title) == null)
			return false;
		return true;
	}

	public boolean checkExistById(int id) {
		// kiểm tra xem target id có tồn tại dưới database chưa
		return targetRepository.findById(id).isPresent();
	}

	public List<TargetDto> getMenuTargetByCourseId(int id) {
		
		return targetRepository.getMenuTargetByCourseId(id);
	}

}
