package com.nts.reserve.service;

import java.util.List;

import com.nts.reserve.dto.Comment;

public interface CommentService {
	List<Comment> getComments(int displayInfoId, boolean isDetailPage);
	
	int getCommentListSize(int displayInfoId);
}
