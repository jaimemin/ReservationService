package com.nts.reserve.service;

import java.util.List;

import com.nts.reserve.dto.Comment;

public interface CommentService {
	List<Comment> getComments(int displayInfoId);

	Double getCommentAverageScore(int displayInfoId);

	int getCommentCount(int displayInfoId);
}
