package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User, Integer>{

	@Query("SELECT new com.myclass.dto.UserDto(u.id, u.email, u.fullname, u.avatar, u.phone, u.address, r.description) FROM User u LEFT JOIN Role r ON u.roleId = r.id")
	public List<UserDto> getAllUserWithRole();
	
	public User findByEmail(String email);

	public User findByPhone(String phone);

}
