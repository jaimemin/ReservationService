package com.nts.reserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reserve.dto.ReservationInfo;
import com.nts.reserve.service.ReservationService;

@RestController
@RequestMapping(path = "/api")
public class ReservationController {
	private final ReservationService reservationService;
	
	@Autowired
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@PostMapping(value = "/reserve", consumes = "applicaiton/json")
	public ReservationInfo reserve(@RequestBody ReservationInfo reservation) {
		int reservationInfoId = reservationService.addReservation(reservation);
		
		return reservationService.getReservationInfo(reservationInfoId);
	}
}
