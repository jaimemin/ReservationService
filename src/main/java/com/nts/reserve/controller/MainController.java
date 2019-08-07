package com.nts.reserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

	@GetMapping(path = "/")
	public String mainPage() {
		return "mainpage";
	}

	@GetMapping(path = "/detail/{displayInfoId}")
	public String detail(@PathVariable("displayInfoId") int displayInfoId, Model model) {
		model.addAttribute("displayInfoId", displayInfoId);

		return "detail";
	}

	@GetMapping(path = "/review/{displayInfoId}")
	public String allReview(@PathVariable("displayInfoId") int displayInfoId, Model model) {
		model.addAttribute("displayInfoId", displayInfoId);

		return "review";
	}
}
