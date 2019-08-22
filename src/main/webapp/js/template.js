const thumbnailTemplate = (listIndex) => (
	`
	<li class="item" id="${listIndex}" style="display: inner-block;">
		<a class="anchor">
			<span class="spr_book ico_del">삭제</span>
		</a>
		<img src="" width="130" alt="댓글 첨부 사진" class="item_thumb">
	</li>
	`
);

const replaceTemplate = (product) => (
	`
	<li class="item">
		<a href="/Reservation/detail/${product.displayInfoId}" class="item_book">
			<div class="item_preview">
				<img alt="${product.description}" class="img_thumb" src="/Reservation/${product.saveFileName}">
				<span class="img_border"></span>
			</div>
			<div class="event_txt">
				<h4 class="event_txt_tit"> 
					<span>${product.description}</span> 
					<small class="sm">${product.placeName}</small> 
				</h4>
				<p class="event_txt_dsc">${product.content}</p>
			</div>
		</a>
	</li>
	`
);

const commentWithoutImageTemplate = (commentInfo) => (
	`
	<li class="list_item">
		<div>
			<div class="review_area">
				<h4 class="resoc_name">${commentInfo.productDescription}</h4>
				<p class="review">${commentInfo.comment}</p>
			</div>
			<div class="info_area">
				<div class="review_info">
					<span class="grade">${commentInfo.score}</span> 
					<span class="name">${commentInfo.reservationEmail}</span>
					<span class="date">${commentInfo.createdDate} 방문</span>
				</div>
			</div>
		</div>
	</li>
	`
);

const commentWithImageTemplate = (commentInfo) => (
	`
	<li class="list_item">
		<div>
			<div class="review_area">
				<div class="thumb_area">
					<a href="/Reservation/commentImage/${commentInfo.id}" class="thumb" title="이미지 크게 보기"> <img
						width="90" height="90" class="img_vertical_top"
						src="/Reservation/api/review-write/${commentInfo.id}/image" alt="댓글 이미지">
					</a> 
					<span class="img_count" style="display: none;">1</span>
				</div>
				<h4 class="resoc_name">${commentInfo.productDescription}</h4>
				<p class="review">${commentInfo.comment}</p>
			</div>
			<div class="info_area">
				<div class="review_info">
					<span class="grade">${commentInfo.score}</span>
					<span class="name">${commentInfo.reservationEmail}</span>
					<span class="date">${commentInfo.createdDate} 방문</span>
				</div>
			</div>
		</div>
	</li>
	`
);

const productImageTemplate = (image, displayInfo) => (	
	`
	<li class="item" style="width: 414px;">
		<img alt="공연 포스터" class="img_thumb" src="/Reservation/${image.saveFileName}">
		<span class="img_bg"></span>
		<div class="visual_txt">
			<div class="visual_txt_inn">
				<h2 class="visual_txt_tit">
					<span>${displayInfo.placeName}</span>
				</h2>
				<p class="visual_txt_dsc"></p>
			</div>
		</div>
	</li>
	`
);

const detailTemplate = (displayInfo) => (
	`
	<div class="detail_area_wrap">
		<div class="detail_area">
			<div class="detail_info">
				<h3 class="blind">상세정보</h3>
				<ul class="detail_info_group">
					<li class="detail_info_lst">
						<strong class="in_tit">[소개]</strong>
						<p class="in_dsc">${displayInfo.productContent}</p>
					</li>
					<li class="detail_info_lst"><strong class="in_tit">[공지사항]</strong>
						<ul class="in_img_group">
							<li class="in_img_lst">
								<img alt="" class="img_thumb" src="https://ssl.phinf.net/naverbooking/20170131_238/14858250829398Pnx6_JPEG/%B0%F8%C1%F6%BB%E7%C7%D7.jpg?type=a1000">
							</li>
						</ul>
					</li>
					<!-- <li class="detail_info_lst"> <strong class="in_tit">[공연정보]</strong>
						<ul class="in_img_group">
							<li class="in_img_lst"> <img alt="" class="img_thumb" src="https://ssl.phinf.net/naverbooking/20170131_255/1485825099482NmYMe_JPEG/%B0%F8%BF%AC%C1%A4%BA%B8.jpg?type=a1000"> </li>
						</ul>
					</li> -->
				</ul>
			</div>
		</div>
	</div>
	`
);

const directionTemplate = (displayInfo, displayInfoImage) => (
	`
	<div class="detail_location hide">
		<div class="box_store_info no_topline">
			<a href="#" class="store_location" title="지도웹으로 연결"> 
				<img class="store_map img_thumb" alt="map" src="/Reservation//${displayInfoImage.saveFileName}">
				<span class="img_border"></span> 
				<span class="btn_map"><i class="spr_book2 ico_mapview"></i></span>
			</a>
			<h3 class="store_name">${displayInfo.productDescription}</h3>
			<div class="store_info">
				<div class="store_addr_wrap">
					<span class="fn fn-pin2"></span>
					<p class="store_addr store_addr_bold">${displayInfo.placeStreet}</p>
					<p class="store_addr">
						<span class="addr_old">지번</span> <span class="addr_old_detail">${displayInfo.placeLot}</span>
					</p>
					<p class="store_addr addr_detail">${displayInfo.placeName}</p>
				</div>
				<div class="lst_store_info_wrap">
					<ul class="lst_store_info">
						<li class="item">
							<span class="item_lt"> 
								<i class="fn fn-call2"></i> <span class="sr_only">전화번호</span>
							</span> 
							<span class="item_rt"> <a href="tel:${displayInfo.telephone}" class="store_tel">${displayInfo.telephone}</a></span>
						</li>
					</ul>
				</div>
			</div>
			<!-- [D] 모바일 브라우저에서 접근 시 column2 추가와 btn_navigation 요소 추가 -->
			<div class="bottom_common_path column2">
				<a href="#" class="btn_path"> 
					<i class="fn fn-path-find2"></i>
					<span>길찾기</span>
				</a> 
				<a href="#" class="btn_navigation before"> 
					<i class="fn fn-navigation2"></i> <span>내비게이션</span>
				</a>
			</div>
		</div>
	</div>
	`
);