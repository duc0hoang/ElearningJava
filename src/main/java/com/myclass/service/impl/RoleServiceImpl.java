package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.myclass.dto.AddRoleDto;
import com.myclass.dto.RoleDto;
import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;
import com.myclass.service.RoleService;

@Service
@Transactional(rollbackOn = Exception.class)
public class RoleServiceImpl implements RoleService {
	private RoleRepository roleRepository;

	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public List<RoleDto> getAllRole() {
		// chuyển entity sang dto
		List<Role> roleList = roleRepository.findAll();
		List<RoleDto> roleDtoList = new ArrayList<RoleDto>();
		for (Role role : roleList) {
			RoleDto roleDto = new RoleDto(role.getId(), role.getName(), role.getDescription());
			roleDtoList.add(roleDto);
		}
		return roleDtoList;
	}

	public RoleDto getRoleById(int id) {
		// chuyển entity sang dto
		Role role = roleRepository.findById(id).get();
		return new RoleDto(role.getId(), role.getName(), role.getDescription());
	}

	public void deleteById(int id) {
		// xóa role dưới database
		roleRepository.deleteById(id);
	}

	public void add(AddRoleDto entity) {
		// thêm role mới
		roleRepository.save(new Role(0, entity.getName(), entity.getDescription()));
	}

	public void edit(RoleDto entity) {
		// xửa role
		roleRepository.save(new Role(entity.getId(), entity.getName(), entity.getDescription()));
	}

	public boolean checkExistById(int id) {
		// kiểm tra xem role id có tồn tại dưới database chưa
		return roleRepository.findById(id).isPresent();
	}

	public boolean checkExistByName(String name) {
		// kiểm tra xem role name có tồn tại dưới database chưa
		if (roleRepository.findByName(name) == null)
			return false;
		return true;
	}
}
