package com.myclass.service;

import java.util.List;

import com.myclass.dto.AddRoleDto;
import com.myclass.dto.RoleDto;

public interface RoleService {

	List<RoleDto> getAllRole();

	RoleDto getRoleById(int id);

	void deleteById(int id);

	void add(AddRoleDto entity);

	void edit(RoleDto entity);

	boolean checkExistById(int id);

	boolean checkExistByName(String name);

}
