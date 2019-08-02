package com.nts.reserve.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.Comment;
import com.nts.reserve.dto.CommentImage;

@Repository
public interface CommentDao {
	List<Comment> selectComments(int displayInfoId);

	List<CommentImage> selectCommentImages(int commentId);
}
