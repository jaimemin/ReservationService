package com.nts.reserve.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nts.reserve.dto.DisplayInfoResponse;
import com.nts.reserve.dto.Product;
import com.nts.reserve.dto.ReservationInfo;
import com.nts.reserve.service.CommentService;
import com.nts.reserve.service.DisplayInfoService;
import com.nts.reserve.service.ProductService;
import com.nts.reserve.service.ReservationService;

@Controller
@Validated
public class ViewController {
	private static final int MAX_PASSED_DAY = 5;
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy. MM. dd.");
	private final ProductService productService;
	private final ReservationService reservationService;
	private final DisplayInfoService displayInfoService;
	private final CommentService commentService;

	@Autowired
	public ViewController(ProductService productService
			, ReservationService reservationService
			, DisplayInfoService displayInfoService
			, CommentService commentService) {
		this.productService = productService;
		this.reservationService = reservationService;
		this.displayInfoService = displayInfoService;
		this.commentService = commentService;
	}

	@GetMapping(path = "/")
	public String mainPage(@CookieValue(value = "reservationEmail", required = false) String reservationEmail,
			Model model) {
		model.addAttribute("reservationEmail", reservationEmail);

		return "mainpage";
	}
	
	@GetMapping(path = "/products/{productId}")
	public String thumbnailDetail(
			@Valid @Positive(message = "invalid productId: must be over zero")
			@PathVariable("productId") int productId) {
		List<Product> products= productService.getProductInfos(productId);
		int displayInfoId = products.get(0).getDisplayInfoId();
		
		return "redirect:/detail/" + displayInfoId;
	}

	@GetMapping(path = "/detail/{displayInfoId}")
	public String detail(
			@Valid @Positive(message = "invalid displayInfoId: must be over zero") 
			@PathVariable("displayInfoId") int displayInfoId,
			@CookieValue(value = "reservationEmail", required = false) String reservationEmail, 
			Model modelMap) {
		modelMap.addAttribute("displayInfoId", displayInfoId);
		modelMap.addAttribute("reservationEmail", reservationEmail);

		return "detail";
	}

	@GetMapping(path = "/review/{displayInfoId}")
	public String allReview(
			@Valid @Positive(message = "invalid displayInfoId: must be over zero") 
			@PathVariable("displayInfoId") int displayInfoId,
			Model model) {
		model.addAttribute("displayInfoId", displayInfoId);

		return "review";
	}

	@GetMapping(path = "/reserve/{displayInfoId}")
	public String reserve(
			@Valid @PathVariable("displayInfoId") 
			@Positive(message = "invalid displayInfoId: must be over zero") int displayInfoId,
			@CookieValue(value = "reservationEmail", required = false) String reservationEmail, 
			ModelMap modelMap) {
		DisplayInfoResponse displayInfoResponse = displayInfoService.getDisplayInfoResponse(displayInfoId, false);

		modelMap.addAttribute("displayInfoId", displayInfoId);
		modelMap.addAttribute("displayInfo", displayInfoService.getDisplayInfo(displayInfoId));
		modelMap.addAttribute("productImage", displayInfoResponse.getProductImages());
		modelMap.addAttribute("displayInfoResponse", displayInfoResponse);
		modelMap.addAttribute("reservationDate", getReservationDate());
		modelMap.addAttribute("reservationEmail", reservationEmail);
		
		return "reserve";
	}

	@GetMapping(path = "/my-reservation")
	public String myReservation(@CookieValue(value = "reservationEmail", required = false) String reservationEmail,
			ModelMap modelMap) {
		if (StringUtils.isEmpty(reservationEmail)) {
			return "redirect:/booking-login";
		}
		
		List<ReservationInfo> confirmedList = reservationService.getConfirmedReservationInfos(reservationEmail);
		List<ReservationInfo> usedList = reservationService.getUsedReservationInfos(reservationEmail);
		List<ReservationInfo> canceledList = reservationService.getCanceledReservationInfos(reservationEmail);
		int totalListSize = confirmedList.size() + usedList.size() + canceledList.size();

		modelMap.addAttribute("confirmedList", confirmedList);
		modelMap.addAttribute("usedList", usedList);
		modelMap.addAttribute("canceledList", canceledList);
		modelMap.addAttribute("totalReservation", totalListSize);
		modelMap.addAttribute("reservationEmail", reservationEmail);

		return "myreservation";
	}

	@GetMapping(path = "/booking-login")
	public String bookingLogin() {
		return "bookinglogin";
	}
	
	@GetMapping(path = "/review-write/{reservationInfoId}")
	public String reviewWrite(@CookieValue(value = "reservationEmail", required = false) String reservationEmail, 
			@Valid @Positive(message = "invalid reservationId: must be over zero") 
			@PathVariable("reservationInfoId") int reservationInfoId, 
			ModelMap modelMap) {
		if (StringUtils.isEmpty(reservationEmail)) {
			return "redirect:/booking-login";
		}
		
		ReservationInfo reservation = reservationService.getReservationInfo(reservationInfoId);
		
		modelMap.addAttribute("reservation", reservation);
		modelMap.addAttribute("productDescription", 
			displayInfoService.getDisplayInfo(reservation.getDisplayInfoId()).getProductDescription());
		
		return "reviewWrite";
	}
	
	@GetMapping(path = "/commentImage/{commentId}")
	public String commentImage(@Valid @Positive(message = "invalid commentId: must be over zero")
		@PathVariable("commentId") int commentId, 
		Model model) {
		model.addAttribute("commentImages", commentService.getComment(commentId).getCommentImages());
		
		return "commentImage";
	}

	private String getReservationDate() {
		return DATE_TIME_FORMATTER
				.format(LocalDate
						.now()
						.plusDays(new Random().nextInt(MAX_PASSED_DAY) + 1));
	}
}
