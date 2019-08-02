let productImageTemplate = (image, displayInfo) => (	
	`
	<li class="item" style="width: 414px;">
		<img alt="공연 포스터" class="img_thumb" src="http://127.0.0.1:8080/Reservation/${image.saveFileName}">
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

let detailTemplate = (displayInfo) => (
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

let directionTemplate = (displayInfo, displayInfoImage) => (
	`
	<div class="detail_location hide">
		<div class="box_store_info no_topline">
			<a href="#" class="store_location" title="지도웹으로 연결"> 
				<img class="store_map img_thumb" alt="map" src="http://127.0.0.1:8080/Reservation//${displayInfoImage.saveFileName}">
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

let appendTotalSpanTag = (count) => {
	let totalNumber = document.createElement("span");
	totalNumber.innerText = count;

	let off = document.querySelector(".figure_pagination > .off");
	off.appendChild(totalNumber);
}

let changeCurrentIndex = (index) => {
	let currentIndex = document.createElement("span");
	currentIndex.classList.add("num");
	currentIndex.innerText = index;
	
	let figurePagination = document.querySelector(".figure_pagination");
	figurePagination.replaceChild(currentIndex, figurePagination.firstElementChild);
}

let createProductImageUnorderedList = (HTML) => {
	let unorderedList = document.createElement("ul");
	unorderedList.classList.add("visual_img", "swipe");
	unorderedList.innerHTML = HTML;
	
	return unorderedList;
}

let showProductImages = (productImageTemplates) => {
	let imageCount = productImageTemplates.length;
	appendTotalSpanTag(imageCount);
	
	let resultHTML = "";
	productImageTemplates.forEach((template) => {
		resultHTML += template;
	});
	
	let previousButton = document.querySelector(".prev");
	let nextButton = document.querySelector(".nxt");
	
	if (imageCount === 1) {
		previousButton.classList.add("hideDisplay");
		nextButton.classList.add("hideDisplay");
	}
	
	let visualContainer = document.querySelector(".container_visual");
	let visualImage = createProductImageUnorderedList(resultHTML);
	
	visualContainer.appendChild(visualImage);
}

let createProductImageTemplates = (productImages, displayInfo) => {
	let templates = [];

	productImages.forEach((image) => {
		templates.push(productImageTemplate(image, displayInfo));
	});
	
	return templates;
}

let fillProductContentTemplate = (displayInfo) => {
	let productContent = displayInfo.productContent;
	let productContentTemplate = document.querySelector(".store_details > .dsc");
	
	productContentTemplate.innerText = productContent;
}

changeImageOrder = (productImages) => {
	productImages.insertAdjacentElement("beforeend", productImages.firstElementChild);

	let visualImage = document.createElement("ul");
	visualImage.classList.add("visual_img", "swipe");
	visualImage.innerHTML = productImages.innerHTML;

	return visualImage;
}

let createDetailDirectionTemplate = (displayInfo, displayInfoImage) => {
	let sectionInfoTab = document.querySelector(".section_info_tab");
	sectionInfoTab.innerHTML += detailTemplate(displayInfo);
	sectionInfoTab.innerHTML += directionTemplate(displayInfo, displayInfoImage);
}

let showPreviousImage = (productImages, index) => {
	let visualImage = changeImageOrder(productImages);
	visualImage.classList.add("placeRight");
	
	let visualContainer = document.querySelector(".container_visual");
	visualContainer.replaceChild(visualImage, productImages);
	
	setTimeout(() => {
		let newProductImages = visualContainer.firstElementChild;
		newProductImages.classList.add("swipeRight");
		newProductImages.addEventListener("transitionend", () => {
			changeCurrentIndex(index);
		});
	}, 0);
}

let showNextImage = (productImages, index) => {
	if (productImages.classList.contains("swipeRight")) {
		productImages.classList.remove("swipeRight");
	} else if (productImages.classList.contains("swipeLeft") === false) {
		productImages.classList.add("swipeLeft");
	}
	
	productImages.addEventListener("transitionend", () => {
		let visualImage = changeImageOrder(productImages);
		let visualContainer = document.querySelector(".container_visual");
		visualContainer.replaceChild(visualImage, productImages);

		changeCurrentIndex(index);
	});
}

let swipeProductImage = (event) => {
	let figurePagination = document.querySelector(".figure_pagination");
	let currentPageIndex = parseInt(figurePagination.firstElementChild.innerHTML);
	let index = (currentPageIndex === 1) ? 2 : 1;
	
	let clickedButton = event.currentTarget;
	let productImages = document.querySelector(".visual_img");
	
	if (clickedButton.classList.contains("prev")) {
		showPreviousImage(productImages, index);
	} else if (clickedButton.classList.contains("nxt")) {
		showNextImage(productImages, index);
	}
}

let showProductContent = (event) => {
	let clickedButton = event.currentTarget;
	let productContent = document.querySelector(".store_details");
	let openButton = document.querySelector("._open");
	let closeButton = document.querySelector("._close");
	
	if(clickedButton.classList.contains("_open")) {
		productContent.classList.remove("close3");
		openButton.classList.add("hideDisplay");
		openButton.classList.remove("show");
		closeButton.classList.add("show");
		closeButton.classList.remove("hideDisplay");
	} else if (clickedButton.classList.contains("_close")) {
		productContent.classList.add("close3");
		openButton.classList.add("show");
		openButton.classList.remove("hideDisplay");
		closeButton.classList.add("hideDisplay");
		closeButton.classList.remove("show");
	}
}

let showDetail = (event) => {
	let tab = event.target.closest("li");
	let detail = document.querySelector("#detailTab");
	let direction = document.querySelector("#directionTab");
	let detailAreaWrap = document.querySelector(".detail_area_wrap");
	let detailLocation = document.querySelector(".detail_location");
	
	if(tab.classList.contains("_detail")) {
		if(detail.classList.contains("active") === false) {
			detail.classList.add("active");
			direction.classList.remove("active");
			detailAreaWrap.classList.remove("hide");
			detailLocation.classList.add("hide");
		}
	}
}

let showDirection = (event) => {
	let tab = event.target.closest("li");
	let detail = document.querySelector("#detailTab");
	let direction = document.querySelector("#directionTab");
	let detailAreaWrap = document.querySelector(".detail_area_wrap");
	let detailLocation = document.querySelector(".detail_location");

	if(tab.classList.contains("_path")) {
		if(direction.classList.contains("active") === false) {
			direction.classList.add("active");
			detail.classList.remove("active");
			detailLocation.classList.remove("hide");
			detailAreaWrap.classList.add("hide");
		}
	}
}

let registerClickEvent = () => {
	let previousButton = document.querySelector(".prev");
	let nextButton = document.querySelector(".nxt");
	let openButton = document.querySelector("._open");
	let closeButton = document.querySelector("._close");
	let reserveButton = document.querySelector(".bk_btn");
	let detailTab = document.querySelector("._detail");
	let directionTab = document.querySelector("._path");

	previousButton.addEventListener("click", swipeProductImage);
	nextButton.addEventListener("click", swipeProductImage);
	openButton.addEventListener("click", showProductContent);
	closeButton.addEventListener("click", showProductContent);
	reserveButton.addEventListener("click", () => {
		location.href = "/Reservation/htmls/reserve.html";
	});
	detailTab.addEventListener("click", showDetail);
	directionTab.addEventListener("click", showDirection);
}

let requestDatas = () => {
	let xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = () => {
		if(xmlHttpRequest.status >= ERROR_STATUS) {
			alert("오류가 발생했습니다");
			return;
		}
	
		if(xmlHttpRequest.readyState === COMPLETE_STATE) {
			let datas = JSON.parse(xmlHttpRequest.responseText);
    		
			productImageTemplates = createProductImageTemplates(datas.productImages, datas.displayInfo);
			showProductImages(productImageTemplates);
			
			fillProductContentTemplate(datas.displayInfo);
			
			createCommentAverageScore(datas.averageCommentScore);
			createCommentCount(datas.comments);
			createDetailPageComments(datas.comments, datas.displayInfo);
			
			createDetailDirectionTemplate(datas.displayInfo, datas.displayInfoImage);
			
			registerClickEvent();
    	}
	}
    
	let displayInfoId = document.querySelector(".displayInfoId").id;
	let url = `/Reservation/api/products/${displayInfoId}`;
	xmlHttpRequest.open("GET", url);
	xmlHttpRequest.send();
}

document.addEventListener("DOMContentLoaded", () => {
    requestDatas();
});
