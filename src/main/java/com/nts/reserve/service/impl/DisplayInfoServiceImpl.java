package com.nts.reserve.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dao.CommentDao;
import com.nts.reserve.dao.DisplayInfoDao;
import com.nts.reserve.dao.DisplayInfoImageDao;
import com.nts.reserve.dao.ProductImageDao;
import com.nts.reserve.dao.ProductPriceDao;
import com.nts.reserve.dto.DisplayInfoResponse;
import com.nts.reserve.service.DisplayInfoService;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DisplayInfoServiceImpl.class);
	private final CommentDao commentDao;
	private final DisplayInfoDao displayInfoDao;
	private final DisplayInfoImageDao displayInfoImageDao;
	private final ProductImageDao productImageDao;
	private final ProductPriceDao productPriceDao;

	@Autowired
	public DisplayInfoServiceImpl(CommentDao commentDao
			, DisplayInfoDao displayInfoDao
			, DisplayInfoImageDao displayInfoImageDao
			, ProductImageDao productImageDao
			, ProductPriceDao productPriceDao) {
		this.commentDao = commentDao;
		this.displayInfoDao = displayInfoDao;
		this.displayInfoImageDao = displayInfoImageDao;
		this.productImageDao = productImageDao;
		this.productPriceDao = productPriceDao;
	}

	@Override
	public DisplayInfoResponse getDisplayInfoResponse(int displayInfoId) {
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();
		
		if(displayInfoId >= 1 && displayInfoId <= 59) {
			displayInfoResponse.setAverageCommentScore(commentDao.selectCommentAverageScore(displayInfoId));
			displayInfoResponse.setComments(commentDao.selectComments(displayInfoId));
			displayInfoResponse.setDisplayInfo(displayInfoDao.selectDisplayInfo(displayInfoId));
			displayInfoResponse.setDisplayInfoImage(displayInfoImageDao.selectDisplayInfoImage(displayInfoId));
			displayInfoResponse.setProductImages(productImageDao.selectProductImages(displayInfoId));
			displayInfoResponse.setProductPrices(productPriceDao.selectProductPrices(displayInfoId));
			
			return displayInfoResponse;
		} else {
			LOGGER.error("invalid displayInfoId");
			throw new RuntimeException("invalid displayInfoId");
		}
	}
}
