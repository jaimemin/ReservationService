package com.nts.reserve.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@GetMapping(path = "/login")
	public String login(@RequestParam(name = "resrv_email") String reservationEmail, HttpServletResponse response) {
		if (StringUtils.isEmpty(reservationEmail)) {
			throw new IllegalArgumentException("reservation email is not valid");
		}
		
		Cookie cookie = new Cookie("reservationEmail", reservationEmail);
		cookie.setMaxAge(-1);
		response.addCookie(cookie);

		return "redirect:/my-reservation";
	}
	
	@GetMapping(path = "/logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("reservationEmail", null);
		cookie.setMaxAge(0);	
		response.addCookie(cookie);
		
		return "redirect:/";
	}
}
