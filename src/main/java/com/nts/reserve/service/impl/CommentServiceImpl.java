package com.nts.reserve.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reserve.configuration.FileManager;
import com.nts.reserve.dao.CommentDao;
import com.nts.reserve.dao.FileInfoDao;
import com.nts.reserve.dto.Comment;
import com.nts.reserve.dto.FileInfo;
import com.nts.reserve.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	private final CommentDao commentDao;
	private final FileInfoDao fileInfoDao;
	private final FileManager fileManager;

	@Autowired
	public CommentServiceImpl(CommentDao commentDao, 
			FileInfoDao fileInfoDao, 
			FileManager fileManager) {
		this.commentDao = commentDao;
		this.fileInfoDao = fileInfoDao;
		this.fileManager = fileManager;
	}

	@Override
	public List<Comment> getComments(int displayInfoId, boolean isDetailPage) {
		return commentDao.selectComments(displayInfoId, isDetailPage);
	}

	@Override
	@Transactional
	public void addComment(Comment comment, List<MultipartFile> imageFiles) throws IOException {
		List<FileInfo> fileInfos = new ArrayList<>();

		fileInfos = fileManager.saveFiles(imageFiles);

		commentDao.insertComment(comment);

		if (CollectionUtils.isEmpty(fileInfos) == false) {
			fileInfoDao.insertFileInfos(fileInfos);
			commentDao.insertCommentImages(comment.getReservationInfoId(), 
					comment.getCommentId(), 
					fileInfos);
		}

	}

}
