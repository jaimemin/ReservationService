package com.nts.reserve.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

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

@RestController
@RequestMapping(path = "/api")
public class ProductController {
	private final ProductService productService;
	private final DisplayInfoService displayInfoService;

	@Autowired
	public ProductController(ProductService productService, DisplayInfoService displayInfoService) {
		this.productService = productService;
		this.displayInfoService = displayInfoService;
	}

	@GetMapping("/products")
	public List<Product> getProducts(
			@Valid @NotNull @PositiveOrZero(message = "invalid categoryId: categoryId must not be under zero") 
			@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
			@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
		return productService.getProducts(categoryId, start);
	}

	@GetMapping("/products/{displayInfoId}")
	public DisplayInfoResponse getDisplayInfoResponse(@PathVariable("displayInfoId") int displayInfoId,
				@RequestParam(name = "is-detail-page", required = false, defaultValue = "true") boolean isDetailPage) {
		if (displayInfoId <= 0) {
			throw new IllegalArgumentException("invalid displayInfoId: displayInfoId must be over zero");
		}
		
		return displayInfoService.getDisplayInfoResponse(displayInfoId, isDetailPage);
	}

}
