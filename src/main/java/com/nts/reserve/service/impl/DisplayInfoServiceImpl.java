package com.nts.reserve.service.impl;

import java.util.List;

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
	public DisplayInfoResponse getDisplayInfoResponse(int displayInfoId, boolean isDetailPage) {
		if (displayInfoId <= 0) {
			throw new IllegalArgumentException("invalid displayInfoId");
		}

		List<Comment> comments = commentDao.selectComments(displayInfoId, isDetailPage);
		double scoreAverage;
		
		if(isDetailPage) {
			Double average = commentDao.selectCommentScoreAverage(displayInfoId);
			scoreAverage = (average == null) ? 0.0 : average;
		} else {
			scoreAverage = comments.stream()
					.mapToDouble(Comment::getScore)
					.average()
					.orElse(0.0);
		}
		
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();
		displayInfoResponse.setAverageCommentScore(scoreAverage);
		displayInfoResponse.setComments(comments);
		displayInfoResponse.setDisplayInfo(displayInfoDao.selectDisplayInfo(displayInfoId));
		displayInfoResponse.setDisplayInfoImage(displayInfoImageDao.selectDisplayInfoImage(displayInfoId));
		displayInfoResponse.setProductImages(productImageDao.selectProductImages(displayInfoId));
		displayInfoResponse.setProductPrices(productPriceDao.selectProductPrices(displayInfoId));
		displayInfoResponse.setCommentsSize(commentDao.selectCommentListSize(displayInfoId));
		
		return displayInfoResponse;

	}
}
