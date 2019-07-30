package com.nts.reserve.service;

import java.math.BigDecimal;
import java.util.List;

import com.nts.reserve.dto.UserComment;

public interface UserCommentService {
	List<UserComment> getComments(int displayInfoId);

	BigDecimal getCommentAverageScore(int displayInfoId);

	int getCommentCount(int displayInfoId);
}
