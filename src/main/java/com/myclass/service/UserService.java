package com.myclass.service;

import java.util.List;

import com.myclass.dto.AddUserDto;
import com.myclass.dto.EditUserDto;
import com.myclass.dto.SignUpDto;
import com.myclass.dto.UserDto;

public interface UserService {

	List<UserDto> getAllUserWithRole();

	EditUserDto getUserById(int id);

	void deleteById(int id);

	void add(AddUserDto entity);

	void edit(EditUserDto entity);

	void signUp(SignUpDto dto);

}
