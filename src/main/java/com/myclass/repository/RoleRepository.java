package com.myclass.repository;

import org.springframework.stereotype.Component;

import com.myclass.entity.Role;

@Component
public interface RoleRepository extends BaseRepository<Role, Integer>{

	public Role findByName(String name);

}
