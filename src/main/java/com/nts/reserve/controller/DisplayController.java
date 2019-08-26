package com.nts.reserve.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
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
	private static final String NO_IMAGE_PATH = "/img/noimage.png";
	private final FileService fileService;

	@Value("${image.file.path}")
	private String filePath;

	@Autowired
	public DisplayController(FileService fileService) {
		this.fileService = fileService;
	}

	@GetMapping("/display/{fileId}/image")
	public byte[] displayImage(
			@Valid @Positive(message = "invalid displayInfoId: must be over zero") @PathVariable("fileId") int fileId, 
			HttpServletRequest request)
			throws IOException {
		String saveFileName = filePath + fileService.getFileInfo(fileId).getSaveFileName();
		String defaultImage = request.getServletContext().getRealPath(NO_IMAGE_PATH);
		
		File imageFile = new File(saveFileName);
		String image = imageFile.exists() ? saveFileName : defaultImage;
		
		try (InputStream fileInputStream = new FileInputStream(image)) {
			return IOUtils.toByteArray(fileInputStream);
		}
	}
}
