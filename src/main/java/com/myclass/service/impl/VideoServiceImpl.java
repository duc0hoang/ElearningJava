package com.myclass.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.myclass.dto.AddVideoDto;
import com.myclass.dto.EditVideoDto;
import com.myclass.dto.VideoDto;
import com.myclass.entity.Video;
import com.myclass.repository.VideoRepository;
import com.myclass.service.VideoService;

@Service
@Transactional(rollbackOn = Exception.class)
public class VideoServiceImpl implements VideoService {
	private VideoRepository videoRepository;

	public VideoServiceImpl(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}

	public List<VideoDto> getAllWithCourse() {
		// trả về danh sách video có course title
		return videoRepository.getAllWithCourse();
	}

	public void add(AddVideoDto entity) {
		// thêm video mới
		videoRepository
				.save(new Video(0, entity.getTitle(), entity.getUrl(), entity.getTimeCount(), entity.getCourseId()));
	}

	public EditVideoDto getVideoById(int id) {
		// chuyển entity sang dto
		Video entity = videoRepository.findById(id).get();
		return new EditVideoDto(entity.getId(), entity.getTitle(), entity.getUrl(), entity.getTimeCount(),
				entity.getCourseId());
	}

	public void edit(EditVideoDto entity) {
		// sửa thông tin video
		videoRepository.save(new Video(entity.getId(), entity.getTitle(), entity.getUrl(), entity.getTimeCount(),
				entity.getCourseId()));
	}

	public void deleteById(int id) {
		// xóa video
		videoRepository.deleteById(id);
	}

	public boolean checkExistByTitle(String title) {
		// kiểm tra xem video title có tồn tại dưới database chưa
		if(videoRepository.findByTitle(title) == null)
			return false;
		return true;
	}

	public boolean checkExistById(int id) {
		// kiểm tra xem video id có tồn tại dưới database chưa
		return videoRepository.findById(id).isPresent();
	}

	public List<VideoDto> getMenuVideoByCourseId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkProperty(String orderBy) {
		// TODO Auto-generated method stub
		return false;
	}

	public Page<Video> findAllPaging(String orderBy, int i, int pageSize, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

}
