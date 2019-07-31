package com.nts.reserve.dao;

import java.util.List;

import com.nts.reserve.dto.CommentImage;

public interface CommentImageDao {
	List<CommentImage> selectCommentImages(int commentId);
}
