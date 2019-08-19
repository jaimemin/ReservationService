package com.nts.reserve.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
import com.nts.reserve.dto.ReservationInfo;
import com.nts.reserve.service.DisplayInfoService;
import com.nts.reserve.service.ReservationService;

@Controller
@Validated
public class ViewController {
	private static final int MAX_PASSED_DAY = 5;
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy. MM. dd.");
	private final ReservationService reservationService;
	private final DisplayInfoService displayInfoService;

	@Autowired
	public ViewController(ReservationService reservationService, DisplayInfoService displayInfoService) {
		this.reservationService = reservationService;
		this.displayInfoService = displayInfoService;
	}

	@GetMapping(path = "/")
	public String mainPage(@CookieValue(value = "reservationEmail", required = false) String reservationEmail,
			Model model) {
		model.addAttribute("reservationEmail", reservationEmail);

		return "mainpage";
	}

	@GetMapping(path = "/detail/{displayInfoId}")
	public String detail(
			@Valid @NotNull @Positive(message = "invalid displayInfoId: must be over zero") 
			@PathVariable("displayInfoId") int displayInfoId,
			@CookieValue(value = "reservationEmail", required = false) String reservationEmail, 
			Model modelMap) {
		modelMap.addAttribute("displayInfoId", displayInfoId);
		modelMap.addAttribute("reservationEmail", reservationEmail);

		return "detail";
	}

	@GetMapping(path = "/review/{displayInfoId}")
	public String allReview(
			@Valid @NotNull @Positive(message = "invalid displayInfoId: must be over zero") 
			@PathVariable("displayInfoId") int displayInfoId,
			Model model) {
		model.addAttribute("displayInfoId", displayInfoId);

		return "review";
	}

	@GetMapping(path = "/reserve/{displayInfoId}")
	public String reserve(
			@Valid @PathVariable("displayInfoId") @NotNull 
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

	private String getReservationDate() {
		return DATE_TIME_FORMATTER
				.format(LocalDate
						.now()
						.plusDays(new Random().nextInt(MAX_PASSED_DAY) + 1));
	}
}
