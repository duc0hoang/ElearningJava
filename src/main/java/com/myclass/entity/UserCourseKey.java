package com.myclass.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserCourseKey implements Serializable{

	@Column(name = "user_id")
	int userId;
	
	@Column(name = "course_id")
	int courseId;
}
