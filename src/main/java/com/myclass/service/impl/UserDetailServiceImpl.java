package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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

	public UserDetailServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user == null) throw new UsernameNotFoundException("Email does not exist!");
		
		String roleName = user.getRole().getName();
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(roleName));
		
		return new UserDetailDto(email, user.getPassword(), authorities);
	}

}
