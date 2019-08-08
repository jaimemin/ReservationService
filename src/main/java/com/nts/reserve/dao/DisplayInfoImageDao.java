package com.nts.reserve.dao;

import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.DisplayInfoImage;

@Repository
public interface DisplayInfoImageDao {
	DisplayInfoImage selectDisplayInfoImage(int displayInfoId);
}
