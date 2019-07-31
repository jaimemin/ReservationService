package com.nts.reserve.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dao.DisplayInfoDao;
import com.nts.reserve.dao.DisplayInfoImageDao;
import com.nts.reserve.dto.DisplayInfo;
import com.nts.reserve.dto.DisplayInfoImage;
import com.nts.reserve.service.DisplayInfoService;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {
	private final DisplayInfoDao displayInfoDao;
	private final DisplayInfoImageDao displayInfoImageDao;

	@Autowired
	public DisplayInfoServiceImpl(DisplayInfoDao displayInfoDao, DisplayInfoImageDao displayInfoImageDao) {
		this.displayInfoDao = displayInfoDao;
		this.displayInfoImageDao = displayInfoImageDao;
	}

	@Override
	public DisplayInfo getDisplayInfo(int displayInfoId) {
		return displayInfoDao.selectDisplayInfo(displayInfoId);
	}

	@Override
	public DisplayInfoImage getDisplayInfoImage(int displayInfoId) {
		return displayInfoImageDao.selectDisplayInfoImage(displayInfoId);
	}
}
