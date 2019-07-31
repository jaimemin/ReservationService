package com.nts.reserve.dao;

import java.util.List;

import com.nts.reserve.dto.Comment;

public interface CommentDao {
	List<Comment> selectComments(int displayInfoId);

	Double selectCommentAverageScore(int displayInfoId);

	int selectCommentCount(int displayInfoId);
}
