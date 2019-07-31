package com.nts.reserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reserve.dto.DisplayInfoResponse;
import com.nts.reserve.dto.Product;
import com.nts.reserve.service.DisplayInfoService;
import com.nts.reserve.service.ProductService;
import com.nts.reserve.service.CommentService;

@RestController
@RequestMapping(path = "/api")
public class ProductController {
	private final ProductService productService;
	private final CommentService commentService;
	private final DisplayInfoService displayInfoService;

	@Autowired
	public ProductController(ProductService productService, 
			CommentService commentService,
			DisplayInfoService displayInfoService) {
		this.productService = productService;
		this.commentService = commentService;
		this.displayInfoService = displayInfoService;
	}

	@GetMapping("/products")
	public List<Product> getProducts(
			@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
			@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
		return productService.getProducts(categoryId, start);
	}

	@GetMapping("/products/{displayInfoId}")
	public DisplayInfoResponse getDisplayInfoResponse(@PathVariable("displayInfoId") int displayInfoId) {
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();
		displayInfoResponse.setAverageCommentScore(commentService.getCommentAverageScore(displayInfoId));
		displayInfoResponse.setComments(commentService.getComments(displayInfoId));
		displayInfoResponse.setDisplayInfo(displayInfoService.getDisplayInfo(displayInfoId));
		displayInfoResponse.setDisplayInfoImage(displayInfoService.getDisplayInfoImage(displayInfoId));
		displayInfoResponse.setProductImages(productService.getProductImages(displayInfoId));
		displayInfoResponse.setProductPrices(productService.getProductPrices(displayInfoId));

		return displayInfoResponse;
	}

}
