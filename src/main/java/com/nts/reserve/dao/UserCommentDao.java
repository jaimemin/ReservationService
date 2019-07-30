package com.nts.reserve.dao;

import java.math.BigDecimal;
import java.util.List;

import com.nts.reserve.dto.UserComment;

public interface UserCommentDao {
	List<UserComment> selectComments(int displayInfoId);

	BigDecimal selectCommentAverageScore(int displayInfoId);

	int selectCommentCount(int displayInfoId);
}
