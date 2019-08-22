package com.nts.reserve.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reserve.dto.Comment;
import com.nts.reserve.service.CommentService;

@RestController
@RequestMapping(path = "/api")
public class ReviewWriteController {
	private final CommentService commentService;
	
	@Autowired
	public ReviewWriteController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping("/review-write")
	public Comment writeReview(@RequestParam(value="imageFiles[]", required=false) List<MultipartFile> imageFiles, 
			Comment comment) throws IOException {
		commentService.addComment(comment, imageFiles);
		
		return comment;
	}
	
	@GetMapping("/review-write/{commentId}/image")
	public byte[] commentImage(@Valid @Positive 
			@PathVariable("commentId") int commentId) throws IOException {
		Comment comment = commentService.getComment(commentId);
		String saveFileName = comment.getCommentImages().get(0).getSaveFileName();
		InputStream inputStream = new FileInputStream(saveFileName);
		
		return IOUtils.toByteArray(inputStream);
	}
}
