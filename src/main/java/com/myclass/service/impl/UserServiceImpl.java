package com.myclass.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.myclass.dto.AddUserDto;
import com.myclass.dto.EditUserDto;
import com.myclass.dto.SignUpDto;
import com.myclass.dto.UserDto;
import com.myclass.dto.UserInfoDto;
import com.myclass.dto.UserLoginDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserService;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserDto> getAllUserWithRole() {
		// trả về danh sách user có role name
		return userRepository.getAllUserWithRole();
	}

	public EditUserDto getUserById(int id) {
		// chuyển entity sang dto
		User entity = userRepository.findById(id).get();
		return new EditUserDto(entity.getId(), entity.getFullname(), entity.getPhone(), entity.getAddress(),
				entity.getRoleId());
	}

	public void deleteById(int id) {
		// xóa user
		userRepository.deleteById(id);
	}

	public void add(AddUserDto entity) {
		// thêm user mới với password đã hash
		userRepository.save(new User(0, entity.getEmail(), entity.getFullname(),
				BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt()), "", entity.getAddress(), entity.getPhone(),
				entity.getRoleId()));
	}

	public void edit(EditUserDto entity) {
		// sửa thông tin user
		User user = userRepository.findById(entity.getId()).get();
		userRepository.save(new User(entity.getId(), user.getEmail(), entity.getFullname(), user.getPassword(),
				user.getAvatar(), entity.getAddress(), entity.getPhone(), entity.getRoleId()));
	}

	public void signUp(SignUpDto entity) {
		// đăng ký tài khoản user thì sẽ có role là student
		userRepository.save(new User(0, entity.getEmail(), entity.getFullname(),
				BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt()), "", entity.getAddress(), entity.getPhone(), 3));
	}

	public boolean checkExistById(int id) {
		// kiểm tra xem user id có tồn tại dưới database chưa
		return userRepository.findById(id).isPresent();
	}

	public boolean checkExistByEmail(String email) {
		// kiểm tra xem user email có tồn tại dưới database chưa
		if (userRepository.findByEmail(email) == null)
			return false;
		return true;
	}

	public boolean checkExistByPhone(String phone) {
		// kiểm tra xem user sdt có tồn tại dưới database chưa
		if (userRepository.findByPhone(phone) == null)
			return false;
		return true;
	}

	public UserLoginDto getUserLoginDtoByEmail(String email) {
		// chuyển từ entity sang dto
		User user = userRepository.findByEmail(email);
		return new UserLoginDto(user.getId(), user.getEmail(), user.getFullname(), user.getAvatar(), user.getPhone(),
				user.getAddress(), user.getRole().getDescription());
	}

	public boolean checkPassword(String email, String oldPassword) {
		User user = userRepository.findByEmail(email);
		return BCrypt.checkpw(oldPassword, user.getPassword());
	}

	public void changePassword(String email, String newPassword) {
		User user = userRepository.findByEmail(email);
		user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
		userRepository.save(user);
	}

	public void setNewPassword(int id, String newPassword) {
		User user = userRepository.findById(id).get();
		user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
		userRepository.save(user);
	}

	public String getAvatarById(int id) {
		return userRepository.findById(id).get().getAvatar();
	}

	public void editAvatarById(int id, String image) {
		User user = userRepository.findById(id).get();
		user.setAvatar(image);
		userRepository.save(user);
	}

	public UserInfoDto getInfoByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return new UserInfoDto(user.getFullname(),user.getAvatar(),user.getPhone(),user.getAddress());
	}

	public String getAvatarByEmail(String email) {
		return userRepository.findByEmail(email).getAvatar();
	}

	public void editAvatarByEmail(String email, String upload) {
		User user = userRepository.findByEmail(email);
		user.setAvatar(upload);
		userRepository.save(user);
	}

}
