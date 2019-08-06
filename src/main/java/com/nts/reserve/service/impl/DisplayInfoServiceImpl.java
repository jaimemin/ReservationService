package com.nts.reserve.service.impl;

import java.util.List;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dao.CommentDao;
import com.nts.reserve.dao.DisplayInfoDao;
import com.nts.reserve.dao.DisplayInfoImageDao;
import com.nts.reserve.dao.ProductImageDao;
import com.nts.reserve.dao.ProductPriceDao;
import com.nts.reserve.dto.Comment;
import com.nts.reserve.dto.DisplayInfo;
import com.nts.reserve.dto.DisplayInfoResponse;
import com.nts.reserve.service.DisplayInfoService;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {
	private final CommentDao commentDao;
	private final DisplayInfoDao displayInfoDao;
	private final DisplayInfoImageDao displayInfoImageDao;
	private final ProductImageDao productImageDao;
	private final ProductPriceDao productPriceDao;

	@Autowired
	public DisplayInfoServiceImpl(CommentDao commentDao, DisplayInfoDao displayInfoDao,
			DisplayInfoImageDao displayInfoImageDao, ProductImageDao productImageDao, ProductPriceDao productPriceDao) {
		this.commentDao = commentDao;
		this.displayInfoDao = displayInfoDao;
		this.displayInfoImageDao = displayInfoImageDao;
		this.productImageDao = productImageDao;
		this.productPriceDao = productPriceDao;
	}

	@Override
	public DisplayInfoResponse getDisplayInfoResponse(int displayInfoId) {
		if (displayInfoId <= 0) {
			throw new IllegalArgumentException("invalid displayInfoId");
		}
		
		DisplayInfo displayInfo = displayInfoDao.selectDisplayInfo(displayInfoId);
		
		if(displayInfo == null) {
			throw new NullPointerException("displayInfo returned null");
		}
		
		List<Comment> comments = commentDao.selectComments(displayInfoId);
		double average = comments.stream().mapToDouble(Comment::getScore).average().orElse(0.0);
		
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();
		displayInfoResponse.setAverageCommentScore(average);
		displayInfoResponse.setComments(comments);
		displayInfoResponse.setDisplayInfo(displayInfo);
		displayInfoResponse.setDisplayInfoImage(displayInfoImageDao.selectDisplayInfoImage(displayInfoId));
		displayInfoResponse.setProductImages(productImageDao.selectProductImages(displayInfoId));
		displayInfoResponse.setProductPrices(productPriceDao.selectProductPrices(displayInfoId));

		return displayInfoResponse;

	}
}
