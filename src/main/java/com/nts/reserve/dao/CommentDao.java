package com.nts.reserve.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.Comment;
import com.nts.reserve.dto.CommentImage;

@Repository
public interface CommentDao {
	List<Comment> selectComments(@Param("displayInfoId") int displayInfoId,
			@Param("isDetailPage") boolean isDetailPage);

	List<CommentImage> selectCommentImages(int commentId);
	
	int selectCommentListSize(int displayInfoId);
}
