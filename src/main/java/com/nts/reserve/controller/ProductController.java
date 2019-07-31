package com.nts.reserve.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reserve.dto.DisplayInfo;
import com.nts.reserve.dto.DisplayInfoEntity;
import com.nts.reserve.dto.DisplayInfoImage;
import com.nts.reserve.dto.Product;
import com.nts.reserve.dto.ProductImage;
import com.nts.reserve.dto.ProductPrice;
import com.nts.reserve.dto.UserComment;
import com.nts.reserve.service.DisplayInfoService;
import com.nts.reserve.service.ProductService;
import com.nts.reserve.service.UserCommentService;

@RestController
@RequestMapping(path = "/api")
public class ProductController {
	private final ProductService productService;
	private final UserCommentService userCommentService;
	private final DisplayInfoService displayInfoService;

	@Autowired
	public ProductController(ProductService productService,
			UserCommentService userCommentService,
			DisplayInfoService displayInfoService) {
		this.productService = productService;
		this.userCommentService = userCommentService;
		this.displayInfoService = displayInfoService;
	}

	@GetMapping("/products")
	public List<Product> getProducts(
			@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
			@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
		return productService.getProducts(categoryId, start);
	}

	@GetMapping("/products/{displayInfoId}")
	public DisplayInfoEntity getDisplayInfoEntity(@PathVariable("displayInfoId") int displayInfoId, Model model) {
		BigDecimal averageCommentScore = userCommentService.getCommentAverageScore(displayInfoId);
		List<UserComment> comments = userCommentService.getComments(displayInfoId);
		DisplayInfo displayInfo = displayInfoService.getDisplayInfo(displayInfoId);
		DisplayInfoImage displayInfoImage = displayInfoService.getDisplayInfoImage(displayInfoId);
		List<ProductImage> productImages = productService.getProductImages(displayInfoId);
		List<ProductPrice> productPrices = productService.getProductPrices(displayInfoId);

		DisplayInfoEntity displayInfoEntity = new DisplayInfoEntity();
		displayInfoEntity.setAverageCommentScore(averageCommentScore);
		displayInfoEntity.setComments(comments);
		displayInfoEntity.setDisplayInfo(displayInfo);
		displayInfoEntity.setDisplayInfoImage(displayInfoImage);
		displayInfoEntity.setProductImages(productImages);
		displayInfoEntity.setProductPrices(productPrices);

		model.addAttribute("displayInfoEntity", displayInfoEntity);

		return displayInfoEntity;
	}

}
