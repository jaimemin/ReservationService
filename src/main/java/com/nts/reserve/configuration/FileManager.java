package com.nts.reserve.configuration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reserve.dto.FileInfo;

@Component
public class FileManager {
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
	private static final int MAX_FILE_SIZE = 1024;
	
	@Value("${file.path}")
	private String filePath;

	public List<FileInfo> saveFiles(List<MultipartFile> imageFiles) throws IOException {
		if (CollectionUtils.isEmpty(imageFiles)) {
			return null;
		}
		
		List<FileInfo> fileInfos = new ArrayList<>();
		
		for (MultipartFile imageFile : imageFiles) {
			String fileName = DATE_TIME_FORMATTER.format(LocalDateTime.now()) 
					+ imageFile.getOriginalFilename();
			String saveFileName = filePath + fileName;

			try (
				FileOutputStream fos = new FileOutputStream(saveFileName);
				InputStream is = imageFile.getInputStream();) {
					int readCount = 0;
					byte[] buffer = new byte[MAX_FILE_SIZE];
				
					while ((readCount = is.read(buffer)) != -1) {
						fos.write(buffer, 0, readCount);
				}

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
