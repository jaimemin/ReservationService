package com.nts.reserve.service;

import com.nts.reserve.dto.DisplayInfo;
import com.nts.reserve.dto.DisplayInfoImage;
import com.nts.reserve.dto.DisplayInfoResponse;

public interface DisplayInfoService {
	DisplayInfoResponse getDisplayInfoResponse(int displayInfoId, boolean isDetailPage);
	
	DisplayInfo getDisplayInfo(int displayInfoId);
	
	DisplayInfoImage getDisplayInfoImage(int displayInfoId);
}
