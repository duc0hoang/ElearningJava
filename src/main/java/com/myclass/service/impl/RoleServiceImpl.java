package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.dto.AddRoleDto;
import com.myclass.dto.RoleDto;
import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;
import com.myclass.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	private RoleRepository roleRepository;
	
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public List<RoleDto> getAllRole() {
		List<Role> roleList = roleRepository.findAll();
		List<RoleDto> roleDtoList = new ArrayList<RoleDto>();
		for (Role role : roleList) {
			RoleDto roleDto = new RoleDto(role.getId(), role.getName(), role.getDescription());
			roleDtoList.add(roleDto);
		}
		return roleDtoList;
	}

	public RoleDto getRoleById(int id) {
		Role role = roleRepository.findById(id).get();
		return new RoleDto(role.getId(), role.getName(), role.getDescription());
	}

	public void deleteById(int id) {
		roleRepository.deleteById(id);
	}

	public void add(AddRoleDto entity) {
		roleRepository.save(new Role(0,entity.getName(), entity.getDescription()));
	}

	public void edit(RoleDto entity) {
		roleRepository.save(new Role(entity.getId(),entity.getName(), entity.getDescription()));
	}
}
