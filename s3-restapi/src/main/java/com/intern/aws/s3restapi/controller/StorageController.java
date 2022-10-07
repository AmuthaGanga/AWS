package com.intern.aws.s3restapi.controller;

import static com.intern.aws.s3restapi.constants.S3UploadConstant.UPLOAD_FILE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.intern.aws.s3restapi.model.CustomResponse;
import com.intern.aws.s3restapi.service.StorageService;

@RestController
public class StorageController {
	
	@Autowired
	private StorageService service;
	
	@PostMapping(UPLOAD_FILE)
	public ResponseEntity<CustomResponse> uploadFile(@RequestParam MultipartFile file, @RequestParam(required=false) String key) {
		return service.saveFile(file, key);
	}

}
