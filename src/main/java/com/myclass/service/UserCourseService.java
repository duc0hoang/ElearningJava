package com.myclass.service;

import java.util.List;

import com.myclass.dto.AddUserCourseDto;
import com.myclass.dto.UserCourseDto;

public interface UserCourseService {

	List<UserCourseDto> getAllUserCourse();

	boolean checkUserWithCourse(AddUserCourseDto dto);

	List<UserCourseDto> getAllCourseByUserId(int userId);

	List<UserCourseDto> getAllUserByCourseId(int courseId);

	void addCourse(AddUserCourseDto dto);

}
