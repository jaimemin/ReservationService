package com.nts.reserve.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reserve.service.FileService;

@RestController
@RequestMapping(path = "/api")
public class DisplayController {
	private final FileService fileService;

	@Value("${image.file.path}")
	private String filePath;

	@Autowired
	public DisplayController(FileService fileService) {
		this.fileService = fileService;;
	}

	@GetMapping("/display/{fileId}/image")
	public byte[] displayImage(
			@Valid @Positive(message = "invalid displayInfoId: must be over zero") @PathVariable("fileId") int fileId)
			throws IOException {
		String saveFileName = filePath + fileService.getFileInfo(fileId).getSaveFileName();
		
		try (InputStream fileInputStream = new FileInputStream(saveFileName)) {
			return IOUtils.toByteArray(fileInputStream);
		}
	}
}
