<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
<body>
	<h1>오류가 발생했습니다.</h1>
	<h1>관리자에게 문의해주세요.</h1>

	<!--  [톰캣 설치 디렉토리]/logs/에 있는 로그파일에 기록 -->
	<c:forEach items="${exception.getStackTrace()}" var="stack">
		application.log(${stack.toString()});
	</c:forEach>
</body>
</html>