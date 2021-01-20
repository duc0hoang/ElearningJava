package com.myclass.dto;


public class AddVideoDto {
	private String title;
	private String url;
	private int timeCount;
	private int courseId;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getTimeCount() {
		return timeCount;
	}
	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public AddVideoDto() {
		super();
	}
	public AddVideoDto(String title, String url, int timeCount, int courseId) {
		super();
		this.title = title;
		this.url = url;
		this.timeCount = timeCount;
		this.courseId = courseId;
	}
}
