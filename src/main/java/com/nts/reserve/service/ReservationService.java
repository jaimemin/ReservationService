package com.nts.reserve.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nts.reserve.dto.ReservationInfo;

public interface ReservationService {
	ReservationInfo getReservationInfo(int reservationInfoId);
	
	List<ReservationInfo> getReservationInfos(String reservationEmail, int reservationType);
	
	int modifyReservation(int reservationInfoId);
	
	int addReservation(ReservationInfo reservation);
}
