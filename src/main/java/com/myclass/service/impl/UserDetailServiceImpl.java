package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myclass.dto.UserDetailDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserDetailServiceImpl implements UserDetailsService {
	private UserRepository userRepository;
	
	@Value("${message.username}")
	private String emailIsNotExist;

	public UserDetailServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// lấy ra user theo email
		User user = userRepository.findByEmail(email);
		
		if(user == null) throw new UsernameNotFoundException(emailIsNotExist);
		
		// lấy ra role name của role trong user 
		String roleName = user.getRole().getName();
		
		// tạo list role
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(roleName));
		
		return new UserDetailDto(email, user.getPassword(), authorities);
	}

}
