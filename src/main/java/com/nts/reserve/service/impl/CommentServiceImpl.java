package com.nts.reserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dao.CommentDao;
import com.nts.reserve.dto.Comment;
import com.nts.reserve.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	private final CommentDao commentDao;

	@Autowired
	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Override
	public List<Comment> getComments(int displayInfoId, boolean isDetailPage) {
		return commentDao.selectComments(displayInfoId, isDetailPage);
	}

}
