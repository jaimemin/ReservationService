package com.nts.reserve.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {
	private String comment;
	private int commentId;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private int productId;
	private LocalDateTime reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
	private int score;
	private List<CommentImage> commentImages;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	@JsonProperty("createdDateView")
	public String getCreatedDateView() {
		return createdDate.format(DateTimeFormatter.ofPattern("yyyy.M.d."));
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	@JsonProperty("reservationEmail")
	public String getReservationEmailView() {
		return StringUtils.substring(reservationEmail, 0, 4) + "****";
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<CommentImage> getCommentImages() {
		return commentImages;
	}

	public void setCommentImages(List<CommentImage> commentImages) {
		this.commentImages = commentImages;
	}

	@Override
	public String toString() {
		return "Comment [comment=" + comment + ", commentId=" + commentId + ", commentImages=" + commentImages
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", productId=" + productId
				+ ", reservationDate=" + reservationDate + ", reservationEmail=" + reservationEmail
				+ ", reservationInfoId=" + reservationInfoId + ", reservationName=" + reservationName
				+ ", reservationTelephone=" + reservationTelephone + ", score=" + score + "]";
	}

}
