package com.nts.reserve.controller;

import java.io.FileInputStream;
import java.io.IOException;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reserve.service.DisplayInfoService;

@RestController
@RequestMapping(path = "/api")
public class DisplayController {
	private final DisplayInfoService displayInfoService;
	
	@Value("${image.file.path}")
	private String filePath;
	
	@Autowired
	public DisplayController (DisplayInfoService displayInfoService) {
		this.displayInfoService = displayInfoService;
	}
	
	@GetMapping("/display/{displayInfoId}/image")
	public byte[] displayImage(@Valid @Positive(message = "invalid displayInfoId: must be over zero") 
		@PathVariable("displayInfoId") int displayInfoId) throws IOException {
		String saveFileName = displayInfoService.getDisplayInfoImage(displayInfoId).getSaveFileName();
		
		return IOUtils.toByteArray(new FileInputStream(filePath + saveFileName));
	}
}
