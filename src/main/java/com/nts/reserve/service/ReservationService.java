package com.nts.reserve.service;

import java.util.List;

import com.nts.reserve.dto.ReservationInfo;

public interface ReservationService {
	ReservationInfo getReservationInfo(int reservationInfoId);

	List<ReservationInfo> getConfirmedReservationInfos(String reservationEmail);
	
	List<ReservationInfo> getUsedReservationInfos(String reservationEmail);
	
	List<ReservationInfo> getCanceledReservationInfos(String reservationEmail);

	int cancelReservation(int reservationInfoId);

	int addReservation(ReservationInfo reservation);
}
