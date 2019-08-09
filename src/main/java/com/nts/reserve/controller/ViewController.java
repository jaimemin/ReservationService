package com.nts.reserve.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reserve.service.DisplayInfoService;
import com.nts.reserve.service.ReservationService;

@Controller
public class ViewController {
	private static final int CONFIRMED = 0;
	private static final int USED = 1;
	private static final int CANCELED = 2;
	private static final int MAX_PASSED_DAY = 5;
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
	public String detail(@PathVariable("displayInfoId") int displayInfoId,
			@CookieValue(value = "reservationEmail", required = false) String reservationEmail, Model modelMap) {
		modelMap.addAttribute("displayInfoId", displayInfoId);
		modelMap.addAttribute("reservationEmail", reservationEmail);

		return "detail";
	}

	@GetMapping(path = "/review/{displayInfoId}")
	public String allReview(@PathVariable("displayInfoId") int displayInfoId, Model model) {
		model.addAttribute("displayInfoId", displayInfoId);

		return "review";
	}

	@GetMapping(path = "/reserve/{displayInfoId}")
	public String reserve(@PathVariable("displayInfoId") int displayInfoId, ModelMap modelMap) {
		modelMap.addAttribute("displayInfoId", displayInfoId);
		modelMap.addAttribute("displayInfo", displayInfoService.getDisplayInfo(displayInfoId));
		modelMap.addAttribute("reservationDate", 
				DateTimeFormatter
					.ofPattern("yyyy.M.d.")
					.format(LocalDate
							.now()
							.plusDays(new Random().nextInt(MAX_PASSED_DAY) + 1)));

		return "reserve";
	}

	@GetMapping(path = "/my-reservation")
	public String myReservation(@CookieValue(value = "reservationEmail", required = false) String reservationEmail,
			ModelMap modelMap) {
		if (reservationEmail == null) {
			return "redirect:/booking-login";
		}

		modelMap.addAttribute("confirmedList", reservationService.getReservationInfos(reservationEmail, CONFIRMED));
		modelMap.addAttribute("usedList", reservationService.getReservationInfos(reservationEmail, USED));
		modelMap.addAttribute("canceledList", reservationService.getReservationInfos(reservationEmail, CANCELED));

		return "myreservation";
	}

	@GetMapping(path = "/booking-login")
	public String bookingLogin() {
		return "bookinglogin";
	}

	@GetMapping(path = "/login")
	public String login(@RequestParam(name = "resrv_email") String reservationEmail, HttpServletResponse response) {
		Cookie cookie = new Cookie("reservationEmail", reservationEmail);
		cookie.setMaxAge(-1);
		response.addCookie(cookie);

		return "redirect:/my-reservation";
	}
}
