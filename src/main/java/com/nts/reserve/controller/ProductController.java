package com.nts.reserve.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	private static final String NO_IMAGE_PATH = "/img/noimage.png";
	private final ProductService productService;
	private final DisplayInfoService displayInfoService;

	@Value("${image.file.path}")
	private String filePath;

	@Autowired
	public ProductController(ProductService productService
			, DisplayInfoService displayInfoService) {
		this.productService = productService;
		this.displayInfoService = displayInfoService;
	}

	@GetMapping("/products")
	public List<Product> getProducts(
			@Valid @PositiveOrZero(message = "invalid categoryId: categoryId must not be under zero") 
			@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
			@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
		return productService.getProducts(categoryId, start);
	}

	@GetMapping("/products/{displayInfoId}")
	public DisplayInfoResponse getDisplayInfoResponse(
			@Valid @Positive(message = "invalid displayInfoId: must be over zero") @PathVariable("displayInfoId") int displayInfoId,
			@RequestParam(name = "is-detail-page", required = false, defaultValue = "true") boolean isDetailPage) {
		return displayInfoService.getDisplayInfoResponse(displayInfoId, isDetailPage);
	}

	@GetMapping("/products/{productId}/image")
	public byte[] productImage(
			@Valid @Positive(message = "invalid productId: must be over zero") @PathVariable("productId") int productId, 
			HttpServletRequest request)
			throws IOException {
		String saveFileName = filePath + productService.getProduct(productId).getSaveFileName();
		String defaultImage = request.getServletContext().getRealPath(NO_IMAGE_PATH);
		
		File imageFile = new File(saveFileName);
		String image = imageFile.exists() ? saveFileName : defaultImage;

		try (InputStream fileInputStream = new FileInputStream(image)) {
			return IOUtils.toByteArray(fileInputStream);
		}
	}

}
