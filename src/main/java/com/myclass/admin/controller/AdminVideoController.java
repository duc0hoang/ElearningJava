package com.myclass.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.AddVideoDto;
import com.myclass.dto.EditVideoDto;
import com.myclass.service.CourseService;
import com.myclass.service.VideoService;

@RestController
@RequestMapping("/api/admin/video")
public class AdminVideoController {
	private VideoService videoService;
	private CourseService courseService;

	@Value("${message.id}")
	private String idIsNotExist;

	@Value("${message.title}")
	private String titleIsExist;

	@Value("${message.course}")
	private String courseIsNotExist;

	public AdminVideoController(VideoService videoService, CourseService courseService) {
		this.videoService = videoService;
		this.courseService = courseService;
	}

	@GetMapping("")
	public Object get() {
		try {
			// trả về danh sách video
			return new ResponseEntity<Object>(videoService.getAllWithCourse(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("")
	public Object post(@Valid @RequestBody AddVideoDto entity) {
		try {
			// check xem video title có bị trùng hay không
			if (videoService.checkExistByTitle(entity.getTitle()))
				return new ResponseEntity<Object>(titleIsExist, HttpStatus.BAD_REQUEST);

			// check xem course id có tồn tại hay không
			if (!courseService.checkExistById(entity.getCourseId()))
				return new ResponseEntity<Object>(courseIsNotExist, HttpStatus.BAD_REQUEST);

			videoService.add(entity);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("{id}")
	public Object get(@PathVariable int id) {
		try {
			// check xem video id có tồn tại hay không
			if (!videoService.checkExistById(id))
				return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

			// trả về video theo id gửi lên
			return new ResponseEntity<Object>(videoService.getVideoById(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("{id}")
	public Object put(@RequestBody EditVideoDto entity, @PathVariable int id) {
		try {
			// check xem id gửi lên và id trong video có giống nhau hay không
			if (id != entity.getId())
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

			// check xem video id có tồn tại hay không
			if (!videoService.checkExistById(id))
				return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

			// check xem video title có bị trùng hay không
			if (videoService.checkExistByTitle(entity.getTitle()))
				return new ResponseEntity<Object>(titleIsExist, HttpStatus.BAD_REQUEST);

			// check xem course id có tồn tại hay không
			if (!courseService.checkExistById(entity.getCourseId()))
				return new ResponseEntity<Object>(courseIsNotExist, HttpStatus.BAD_REQUEST);

			// sửa video dưới database
			videoService.edit(entity);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("{id}")
	public Object delete(@PathVariable int id) {
		try {
			// check xem video id có tồn tại hay không
			if (!videoService.checkExistById(id))
				return new ResponseEntity<Object>(idIsNotExist, HttpStatus.BAD_REQUEST);

			// xóa video dưới database
			videoService.deleteById(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
}
