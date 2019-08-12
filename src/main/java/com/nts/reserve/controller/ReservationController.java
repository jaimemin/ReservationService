package com.nts.reserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping(value = "/reserve")
	public ReservationInfo cancel(@RequestBody String reservationId) {
		int reservedId = Integer.parseInt(reservationId);
		reservationService.cancelReservation(reservedId);
		
		return reservationService.getReservationInfo(reservedId);
	}
	
	@PostMapping(value = "/reserve")
	public ReservationInfo reserve(@RequestBody ReservationInfo reservation) {
		reservationService.addReservation(reservation);
		
		return reservationService.getReservationInfo(reservation.getId());
	}
}
