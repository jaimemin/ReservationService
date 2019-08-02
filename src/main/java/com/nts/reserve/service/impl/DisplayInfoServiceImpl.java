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
	public DisplayInfoResponse getDisplayInfoResponse(int displayInfoId) {
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();
		DisplayInfo displayInfo = displayInfoDao.selectDisplayInfo(displayInfoId);

		if (displayInfoId <= 0 || displayInfo == null) {
			throw new IllegalArgumentException("invalid displayInfoId");
		}
		
		List<Comment> comments = commentDao.selectComments(displayInfoId);
		double scoreSum = 0.0;
		for(Comment comment : comments) {
			String email = comment.getReservationEmail();
			email = email.substring(0, 4) + "****";
			
			comment.setReservationEmail(email);
			scoreSum += comment.getScore();
		}
		
		int listSize = comments.size();
		double average = (listSize == 0) ? 0.0 : (double)(scoreSum / listSize);
		
		displayInfoResponse.setAverageCommentScore(average);
		displayInfoResponse.setComments(comments);
		displayInfoResponse.setDisplayInfo(displayInfo);
		displayInfoResponse.setDisplayInfoImage(displayInfoImageDao.selectDisplayInfoImage(displayInfoId));
		displayInfoResponse.setProductImages(productImageDao.selectProductImages(displayInfoId));
		displayInfoResponse.setProductPrices(productPriceDao.selectProductPrices(displayInfoId));

		return displayInfoResponse;

	}
}
