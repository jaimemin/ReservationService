package com.nts.reserve.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Comment writeReview(@RequestParam(value="imageFile", required=false) List<MultipartFile> imageFiles, 
			Comment comment) throws IOException {
		commentService.addComment(comment, imageFiles);
		
		return comment;
	}
}
