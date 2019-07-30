package com.nts.reserve.dao;

import java.util.List;

import com.nts.reserve.dto.UserCommentImage;

public interface UserCommentImageDao {
	List<UserCommentImage> selectCommentImages();
}
