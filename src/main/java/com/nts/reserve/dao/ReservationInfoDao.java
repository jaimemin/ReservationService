package com.nts.reserve.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.ReservationInfo;

@Repository
public interface ReservationInfoDao {
	ReservationInfo selectReservationInfo(int reservationInfoId);
	
	List<ReservationInfo> selectReservationInfos(@Param("reservationEmail") String reservationEmail,
			@Param("reservationType") int reservationType);
	
	int insertReservation(ReservationInfo reservation);
	
	int updateReservation(@Param("reservationInfoId") int reservationInfoId, 
			@Param("reservationEmail") String reservationEmail);
}
