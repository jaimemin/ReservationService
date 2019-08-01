package com.nts.reserve.dao;

import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.DisplayInfo;

@Repository
public interface DisplayInfoDao {
	DisplayInfo selectDisplayInfo(int displayInfoId);
}
