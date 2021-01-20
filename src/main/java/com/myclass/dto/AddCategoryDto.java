package com.myclass.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class AddCategoryDto {
	
	@NotEmpty(message = "The title cannot be blank!")
	@Length(max = 20, min = 4,message = "The titel must be more than 3 and less than 21 characters")
	private String title;
	
	@NotEmpty(message = "The icon cannot be blank!")
	private String icon;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
