package com.nts.reserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dao.UserCommentImageDao;
import com.nts.reserve.dto.UserCommentImage;
import com.nts.reserve.service.UserCommentImageService;

@Service
public class UserCommentImageServiceImpl implements UserCommentImageService {
	private final UserCommentImageDao userCommentImageDao;

	@Autowired
	public UserCommentImageServiceImpl(UserCommentImageDao reservationUserCommentImageDao) {
		this.userCommentImageDao = reservationUserCommentImageDao;
	}

	@Override
	public List<UserCommentImage> getCommentImages() {
		return userCommentImageDao.selectCommentImages();
	}

}
