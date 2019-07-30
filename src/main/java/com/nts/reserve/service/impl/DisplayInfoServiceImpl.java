package com.nts.reserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dao.DisplayInfoDao;
import com.nts.reserve.dto.DisplayInfo;
import com.nts.reserve.service.DisplayInfoService;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {
	private final DisplayInfoDao displayInfoDao;

	@Autowired
	public DisplayInfoServiceImpl(DisplayInfoDao displayInfoDao) {
		this.displayInfoDao = displayInfoDao;
	}

	@Override
	public DisplayInfo getDisplayInfo(int displayInfoId) {
		return displayInfoDao.selectDisplayInfo(displayInfoId);
	}
}
