package com.nts.reserve.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.ReservationPrice;

@Repository
public interface ReservationPriceDao {
	List<ReservationPrice> selectReservationPrices(int reservationInfoId);
	
	int selectTotalPrice(int reservationInfoId);
	
	int insertReservationPrices(List<ReservationPrice> reservationPrices);
}
