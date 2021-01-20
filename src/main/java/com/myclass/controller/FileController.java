package com.myclass.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("apt/file")
public class FileController {

	@Value("${file.upload-dir}")
	private String uploadDir;

	@PostMapping("upload")
	public Object upload(@RequestParam MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();

			String userDirectory = Paths.get("").toAbsolutePath().toString();

			Path path = Paths.get(userDirectory + uploadDir + fileName);
			
			System.out.println(path);

			Files.write(path, file.getBytes());

			return new ResponseEntity<Object>(fileName, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
}
