package com.myclass.dto;


public class AddTargetDto {
	private String title;
	private int courseId;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public AddTargetDto() {
		super();
	}
	public AddTargetDto(String title, int courseId) {
		super();
		this.title = title;
		this.courseId = courseId;
	}
}
