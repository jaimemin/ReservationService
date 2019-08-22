package com.nts.reserve.handler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.uuid.Generators;
import com.nts.reserve.dto.FileInfo;

@Component
public class FileHandler {
	// https://github.com/cowtowncoder/java-uuid-generator
	private static final UUID UUID = Generators.timeBasedGenerator().generate();
	
	@Value("${file.path}")
	private String filePath;

	public List<FileInfo> saveFiles(List<MultipartFile> imageFiles) throws IOException {
		if (CollectionUtils.isEmpty(imageFiles)) {
			return null;
		}
		
		List<FileInfo> fileInfos = new ArrayList<>();
		
		for (MultipartFile imageFile : imageFiles) {
			String fileName = UUID + imageFile.getOriginalFilename();
			String saveFileName = filePath + fileName;

			try (
				FileOutputStream fileOutputStream = new FileOutputStream(saveFileName);
				InputStream inputStream = imageFile.getInputStream();) {
				FileCopyUtils.copy(inputStream, fileOutputStream);

				FileInfo fileInfo = new FileInfo();
				fileInfo.setContentType(imageFile.getContentType());
				fileInfo.setFileName(fileName);
				fileInfo.setSaveFileName(saveFileName);
				fileInfo.setDeleteFlag(0);

				fileInfos.add(fileInfo);
			}
		}
		
		return fileInfos;
	}

}
