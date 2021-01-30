package com.myclass.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class RoleDto {
	@Min(value = 1,message = "Role id must be larger than 0")
	private int id;
	@Pattern(regexp = "^[A-Z]+{4,15}$", message = "Role must is uppercase words and contain from 4 to 15 characters.")
	private String name;
	
	@NotEmpty
	@Length(min = 4, max = 250, message = "Description must be contain from 4 to 250 characters.")
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public RoleDto() {
		super();
	}
	public RoleDto(int id, String name, String description) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
}
