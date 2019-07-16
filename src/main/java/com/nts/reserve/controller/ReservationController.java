package com.nts.reserve.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ReservationController {
	@GetMapping(path="/main")
	public String mainPage() {
		return "/htmls/mainpage.html";
	}
}
