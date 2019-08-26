package com.nts.reserve.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.nts.reserve.handler.FileHandler;
import com.nts.reserve.service.CommentService;
import com.nts.reserve.service.FileService;

@RestController
@RequestMapping(path = "/api")
public class ReviewWriteController {
	private static final String NO_IMAGE_PATH = "/img/noimage.png";
	private final CommentService commentService;
	private final FileService fileService;
	private final FileHandler fileManager;
	
	@Autowired
	public ReviewWriteController(CommentService commentService, 
			FileService fileService, 
			FileHandler fileManager) {
		this.commentService = commentService;
		this.fileService = fileService;
		this.fileManager = fileManager;
	}
	
	@PostMapping("/review-write")
	public Comment writeReview(@RequestParam(value="imageFiles[]", required=false) List<MultipartFile> imageFiles, 
			Comment comment) throws IOException {
		commentService.addComment(comment, fileManager.saveFiles(imageFiles));
		
		return comment;
	}
	
	@GetMapping("/review-write/{commentId}/image")
	public byte[] commentImage(@Valid @Positive 
			@PathVariable("commentId") int commentId, 
			HttpServletRequest request) throws IOException {
		Comment comment = commentService.getComment(commentId);
		
		String saveFileName = comment.getCommentImages().get(0).getSaveFileName();
		String defaultImage = request.getServletContext().getRealPath(NO_IMAGE_PATH);
		
		File imageFile = new File(saveFileName);
		String image = imageFile.exists() ? saveFileName : defaultImage;
		
		try (InputStream fileInputStream = new FileInputStream(image)) {
			return IOUtils.toByteArray(fileInputStream);
		}
	}
	
	@GetMapping("/review-write/image")
	public byte[] expandedCommentImage(@Valid @Positive
			@RequestParam(value="fileId") int fileId, 
			HttpServletRequest request) throws IOException {
		String saveFileName = fileService.getFileInfo(fileId).getSaveFileName();
		String defaultImage = request.getServletContext().getRealPath(NO_IMAGE_PATH);
		
		File imageFile = new File(saveFileName);
		String image = imageFile.exists() ? saveFileName : defaultImage;
		
		try (InputStream fileInputStream = new FileInputStream(image)) {
			return IOUtils.toByteArray(fileInputStream);
		}
	}

}
