package com.nts.reserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reserve.dao.ReservationInfoDao;
import com.nts.reserve.dao.ReservationPriceDao;
import com.nts.reserve.dto.ReservationInfo;
import com.nts.reserve.dto.ReservationPrice;
import com.nts.reserve.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	private static final int CONFIRMED = 0;
	private static final int USED = 1;
	private static final int CANCELED = 2;
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
	public List<ReservationInfo> getConfirmedReservationInfos(String reservationEmail) {
		return reservationInfoDao.selectReservationInfos(reservationEmail, CONFIRMED);
	}
	
	@Override
	public List<ReservationInfo> getUsedReservationInfos(String reservationEmail) {
		return reservationInfoDao.selectReservationInfos(reservationEmail, USED);
	}
	
	@Override
	public List<ReservationInfo> getCanceledReservationInfos(String reservationEmail) {
		return reservationInfoDao.selectReservationInfos(reservationEmail, CANCELED);
	}

	@Override
	public int cancelReservation(int reservationInfoId, String reservationEmail) {
		int updatedCount = reservationInfoDao.updateReservation(reservationInfoId, reservationEmail);
		
		if (updatedCount <= 0) {
			throw new DataRetrievalFailureException(
					"Update Failed: reservationInfoId and reservationEmail doesn't match");
		}
		
		return updatedCount;
	}

	@Override
	@Transactional
	public int addReservation(ReservationInfo reservation) {
		reservationInfoDao.insertReservation(reservation);
		
		int reservationInfoId = reservation.getId();
		List<ReservationPrice> reservationPrices = reservation.getReservationPrices();
		
		for(ReservationPrice reservationPrice : reservationPrices) {
			reservationPrice.setReservationInfoId(reservationInfoId);
		}
		
		reservationPriceDao.insertReservationPrices(reservationPrices);
		
		return reservationInfoId;
	}

}
