package com.nts.reserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dao.DisplayInfoImageDao;
import com.nts.reserve.dto.DisplayInfoImage;
import com.nts.reserve.service.DisplayInfoImageService;

@Service
public class DisplayInfoImageServiceImpl implements DisplayInfoImageService {
	private final DisplayInfoImageDao displayInfoImageDao;

	@Autowired
	public DisplayInfoImageServiceImpl(DisplayInfoImageDao displayInfoImageDao) {
		this.displayInfoImageDao = displayInfoImageDao;
	}

	@Override
	public DisplayInfoImage getDisplayInfoImage(int displayInfoId) {
		return displayInfoImageDao.selectDisplayInfoImage(displayInfoId);
	}

}
