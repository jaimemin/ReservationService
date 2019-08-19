<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta name="description"
   content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport"
   content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>Page Not Found</title>
<link href="/Reservation/css/style.css" rel="stylesheet">
</head>

<body>
   <div id="container">
      <div class="header">
         <header class="header_tit">
            <h1 class="logo">
               <a href="https://m.naver.com/" class="lnk_logo" title="네이버"> <span
                  class="spr_bi ico_n_logo">네이버</span>
               </a> 
               <a href="/Reservation/my-reservation" class="lnk_logo" title="예약"> <span
                  class="spr_bi ico_bk_logo">예약</span>
               </a>
            </h1>
            <a href="/Reservation/booking-login" class="btn_my"> <span
               class="viewReservation" title="예약확인">예약확인</span>
            </a>
         </header>
      </div>
      <hr>
      <div
         style="padding: 100px; text-align: center; background-color: white;">
         <h3>오류가 발생했습니다.</h3>
         <h3>관리자에게 문의해주세요.</h3>
         <a href="/Reservation/">
         	<span style="color: blue">메인 페이지로 돌아가기</span>
         </a>
      </div>
   </div>
   <footer>
      <div class="footer">
         <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및
            환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
         <span class="copyright">© NAVER Corp.</span>
      </div>
   </footer>
</body>
</html>