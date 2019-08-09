package com.nts.reserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reserve.dao.ReservationInfoDao;
import com.nts.reserve.dao.ReservationPriceDao;
import com.nts.reserve.dto.ReservationInfo;
import com.nts.reserve.dto.ReservationPrice;
import com.nts.reserve.service.ReservationService;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
	private final ReservationInfoDao reservationInfoDao;
	private final ReservationPriceDao reservationPriceDao;
	
	@Autowired
	public ReservationServiceImpl(ReservationInfoDao reservationInfoDao,
			ReservationPriceDao reservationPriceDao) {
		this.reservationInfoDao = reservationInfoDao;
		this.reservationPriceDao = reservationPriceDao;
	}
	
	@Override
	public ReservationInfo getReservationInfo(int reservationInfoId) {
		return reservationInfoDao.selectReservationInfo(reservationInfoId);
	}
	
	@Override
	public List<ReservationInfo> getReservationInfos(String reservationEmail, int reservationType) {
		return reservationInfoDao.selectReservationInfos(reservationEmail, reservationType);
	}

	@Override
	public int modifyReservation(int reservationInfoId) {
		return reservationInfoDao.updateReservation(reservationInfoId);
	}

	@Override
	public int addReservation(ReservationInfo reservation) {
		reservationInfoDao.insertReservation(reservation);
		
		List<ReservationPrice> reservationPrices = reservation.getReservationPrices();
		reservationPrices.stream()
			.peek(price -> price.setReservationInfoId(reservation.getId()));
		
		reservationPriceDao.insertReservationPrices(reservationPrices);
		
		return reservation.getId();
	}

}
