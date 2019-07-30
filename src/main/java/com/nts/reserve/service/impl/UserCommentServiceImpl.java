package com.nts.reserve.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dao.UserCommentDao;
import com.nts.reserve.dao.UserCommentImageDao;
import com.nts.reserve.dto.UserComment;
import com.nts.reserve.dto.UserCommentImage;
import com.nts.reserve.service.UserCommentService;

@Service
public class UserCommentServiceImpl implements UserCommentService {
	private final UserCommentDao userCommentDao;
	private final UserCommentImageDao userCommentImageDao;

	@Autowired
	public UserCommentServiceImpl(UserCommentDao userCommentDao,
			UserCommentImageDao userCommentImageDao) {
		this.userCommentDao = userCommentDao;
		this.userCommentImageDao = userCommentImageDao;
	}

	@Override
	public List<UserComment> getComments(int displayInfoId) {
		List<UserComment> comments = userCommentDao.selectComments(displayInfoId);
		List<UserCommentImage> allCommentImages = userCommentImageDao.selectCommentImages();

		for (UserComment comment : comments) {
			List<UserCommentImage> commentImages = new ArrayList<>();

			for (UserCommentImage commentImage : allCommentImages) {
				if (comment.getId() == commentImage.getReservationUserCommentId()) {
					commentImages.add(commentImage);
				}
			}

			comment.setCommentImages(commentImages);
		}

		return comments;
	}

	@Override
	public BigDecimal getCommentAverageScore(int displayInfoId) {
		return userCommentDao.selectCommentAverageScore(displayInfoId);
	}

	@Override
	public int getCommentCount(int displayInfoId) {
		return userCommentDao.selectCommentCount(displayInfoId);
	}

}
