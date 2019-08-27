package com.nts.reserve.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nts.reserve.dto.Comment;
import com.nts.reserve.dto.FileInfo;

public interface CommentService {
	Comment getComment(int commentId);
	
	List<Comment> getComments(int displayInfoId, boolean isDetailPage);

	void addComment(Comment comment, List<FileInfo> fileInfos) throws IOException;
}
