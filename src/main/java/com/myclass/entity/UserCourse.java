package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class UserCourse {
	
	@EmbeddedId
	UserCourseKey id;
	
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	User user;
	
	@ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;
	
	@Column(name = "role_id")
	int roleId;
}
