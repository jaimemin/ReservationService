package com.nts.reserve.service;

import java.util.List;

import com.nts.reserve.dto.ReservationInfo;

public interface ReservationService {
	ReservationInfo getReservationInfo(int reservationInfoId);
	
	List<ReservationInfo> getReservationInfos(String reservationEmail);
	
	int modifyReservation(int reservationInfoId);
	
	void addReservation(ReservationInfo reservation);
}
