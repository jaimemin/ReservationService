<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>확대된 사진</title>
</head>
<body>
	<ul style="list-style-type: none;">
		<c:forEach var="commentImage" items="${commentImages}">
			<li>
				<img src="/Reservation/api/review-write/image?fileId=${commentImage.fileId}" alt="댓글 이미지">
			</li>
		</c:forEach>
	</ul>
</body>
</html>