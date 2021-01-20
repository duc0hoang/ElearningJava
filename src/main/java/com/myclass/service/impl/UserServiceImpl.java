package com.myclass.service.impl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.myclass.dto.AddUserDto;
import com.myclass.dto.EditUserDto;
import com.myclass.dto.SignUpDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserDto> getAllUserWithRole() {
		return userRepository.getAllUserWithRole();
	}

	public EditUserDto getUserById(int id) {
		User entity = userRepository.findById(id).get();
		return new EditUserDto(entity.getId(),entity.getFullname(), entity.getAvatar(),
				entity.getPhone(), entity.getAddress(), entity.getRoleId());
	}

	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

	public void add(AddUserDto entity) {
		userRepository.save(new User(0, entity.getEmail(), entity.getFullname(), BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt()),
				entity.getAvatar(), entity.getAddress(), entity.getPhone(), entity.getRoleId()));
	}

	public void edit(EditUserDto entity) {
		User user = userRepository.findById(entity.getId()).get();
		userRepository.save(new User(entity.getId(), user.getEmail(), entity.getFullname(), user.getPassword(),
				entity.getAvatar(), entity.getAddress(), entity.getPhone(), entity.getRoleId()));
	}

	public void signUp(SignUpDto dto) {
		
	}

}
