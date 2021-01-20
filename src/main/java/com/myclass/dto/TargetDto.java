package com.myclass.dto;

public class TargetDto {
	private int id;
	private String title;
	private String course;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public TargetDto() {
		super();
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public TargetDto(int id, String title, String course) {
		super();
		this.id = id;
		this.title = title;
		this.course = course;
	}
}
