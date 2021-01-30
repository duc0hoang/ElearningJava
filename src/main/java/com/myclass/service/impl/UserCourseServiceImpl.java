package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.myclass.dto.AddUserCourseDto;
import com.myclass.dto.UserAddCourseDto;
import com.myclass.dto.UserCourseDto;
import com.myclass.entity.Course;
import com.myclass.entity.User;
import com.myclass.entity.UserCourse;
import com.myclass.entity.UserCourseKey;
import com.myclass.repository.CourseRepository;
import com.myclass.repository.UserCourseRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserCourseService;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserCourseServiceImpl implements UserCourseService {
	private UserCourseRepository userCourseRepository;
	private UserRepository userRepository;
	private CourseRepository courseRepository;

	public UserCourseServiceImpl(UserCourseRepository userCourseRepository, UserRepository userRepository,
			CourseRepository courseRepository) {
		this.userCourseRepository = userCourseRepository;
		this.userRepository = userRepository;
		this.courseRepository = courseRepository;
	}

	public void addCourse(AddUserCourseDto dto) {
		User user = userRepository.findById(dto.getUserId()).get();
		Course course = courseRepository.findById(dto.getCourseId()).get();
		UserCourseKey key = new UserCourseKey(dto.getUserId(), dto.getCourseId());
		UserCourse userCourse = new UserCourse(key, user, course, user.getRoleId());
		userCourseRepository.save(userCourse);
	}

	public List<UserCourseDto> getAllUserCourse() {
		List<UserCourse> userCourseList = userCourseRepository.findAll();
		List<UserCourseDto> userCourseDtoList = new ArrayList<UserCourseDto>();
		for (UserCourse userCourse : userCourseList) {
			UserCourseDto userCourseDto = new UserCourseDto(userCourse.getUser().getEmail(),
					userCourse.getCourse().getTitle());
			userCourseDtoList.add(userCourseDto);
		}
		return userCourseDtoList;
	}

	public List<UserCourseDto> getAllCourseByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserCourseDto> getAllUserByCourseId(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkUserWithCourse(AddUserCourseDto dto) {
		// TODO Auto-generated method stub
		return false;
	}
}
