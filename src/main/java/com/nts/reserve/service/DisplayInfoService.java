package com.nts.reserve.service;

import com.nts.reserve.dto.DisplayInfo;
import com.nts.reserve.dto.DisplayInfoImage;

public interface DisplayInfoService {
	DisplayInfo getDisplayInfo(int displayInfoId);

	DisplayInfoImage getDisplayInfoImage(int displayInfoId);
}
