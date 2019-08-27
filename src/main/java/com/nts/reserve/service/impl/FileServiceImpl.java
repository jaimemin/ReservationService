package com.nts.reserve.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dao.FileInfoDao;
import com.nts.reserve.dto.FileInfo;
import com.nts.reserve.service.FileService;

@Service
public class FileServiceImpl implements FileService{
	private final FileInfoDao fileInfoDao;
	
	@Autowired
	public FileServiceImpl(FileInfoDao fileInfoDao) {
		this.fileInfoDao = fileInfoDao;
	}

	@Override
	public FileInfo getFileInfo(int fileId) {
		return fileInfoDao.selectFileInfo(fileId);
	}

}
