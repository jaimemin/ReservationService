package com.nts.reserve.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.Comment;
import com.nts.reserve.dto.CommentImage;
import com.nts.reserve.dto.FileInfo;

@Repository
public interface CommentDao {
	Comment selectComment(int commentId);
	
	List<Comment> selectComments(@Param("displayInfoId") int displayInfoId,
			@Param("isDetailPage") boolean isDetailPage);

	List<CommentImage> selectCommentImages(int commentId);
	
	int selectCommentListSize(int displayInfoId);
	
	Double selectCommentScoreAverage(int displayInfoId);
	
	int insertComment(Comment comment);
	
	int insertCommentImages(@Param("reservationInfoId") int reservationInfoId,
			@Param("commentId") int commentId,
			@Param("fileInfos") List<FileInfo> fileInfos);
}
