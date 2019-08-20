package com.nts.reserve.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nts.reserve.dto.Comment;

public interface CommentService {
	List<Comment> getComments(int displayInfoId, boolean isDetailPage);

	void addComment(Comment comment, List<MultipartFile> imageFiles) throws IOException;
}
