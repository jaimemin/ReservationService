package com.nts.reserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dao.CommentDao;
import com.nts.reserve.dao.CommentImageDao;
import com.nts.reserve.dto.Comment;
import com.nts.reserve.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	private final CommentDao commentDao;
	private final CommentImageDao commentImageDao;

	@Autowired
	public CommentServiceImpl(CommentDao commentDao, CommentImageDao commentImageDao) {
		this.commentDao = commentDao;
		this.commentImageDao = commentImageDao;
	}

	@Override
	public List<Comment> getComments(int displayInfoId) {
		List<Comment> comments = commentDao.selectComments(displayInfoId);

		for (Comment comment : comments) {
			comment.setCommentImages(commentImageDao.selectCommentImages(comment.getId()));
		}

		return comments;
	}

	@Override
	public Double getCommentAverageScore(int displayInfoId) {
		return commentDao.selectCommentAverageScore(displayInfoId);
	}

	@Override
	public int getCommentCount(int displayInfoId) {
		return commentDao.selectCommentCount(displayInfoId);
	}

}
