const appendTotalSpanTag = (count) => {
	let totalNumber = document.createElement("span");
	totalNumber.innerText = count;

	let off = document.querySelector(".figure_pagination > .off");
	off.appendChild(totalNumber);
}

const changeCurrentIndex = (index) => {
	let currentIndex = document.createElement("span");
	currentIndex.classList.add("num");
	currentIndex.innerText = index;
	
	let figurePagination = document.querySelector(".figure_pagination");
	figurePagination.replaceChild(currentIndex, figurePagination.firstElementChild);
}

const createProductImageUnorderedList = (HTML) => {
	let unorderedList = document.createElement("ul");
	unorderedList.classList.add("visual_img", "swipe");
	unorderedList.innerHTML = HTML;
	
	return unorderedList;
}

const showProductImages = (productImageTemplates) => {
	let imageCount = productImageTemplates.length;
	appendTotalSpanTag(imageCount);
	
	let resultHTML = "";
	productImageTemplates.forEach((template) => {
		resultHTML += template;
	});
	
	let previousButton = document.querySelector(".prev");
	let nextButton = document.querySelector(".nxt");
	
	if (imageCount === 1) {
		previousButton.classList.add("hide");
		nextButton.classList.add("hide");
	}
	
	let visualContainer = document.querySelector(".container_visual");
	let visualImage = createProductImageUnorderedList(resultHTML);
	
	visualContainer.appendChild(visualImage);
}

const createProductImageTemplates = (productImages, displayInfo) => {
	let templates = [];

	productImages.forEach((image) => {
		templates.push(productImageTemplate(image, displayInfo));
	});
	
	return templates;
}

const fillProductContentTemplate = (displayInfo) => {
	let productContent = displayInfo.productContent;
	let productContentTemplate = document.querySelector(".store_details > .dsc");
	
	productContentTemplate.innerText = productContent;
}

const changeImageOrder = (productImages) => {
	productImages.insertAdjacentElement("beforeend", productImages.firstElementChild);

	let visualImage = document.createElement("ul");
	visualImage.classList.add("visual_img", "swipe");
	visualImage.innerHTML = productImages.innerHTML;

	return visualImage;
}

const createDetailDirectionTemplate = (displayInfo, displayInfoImage) => {
	let sectionInfoTab = document.querySelector(".section_info_tab");
	sectionInfoTab.innerHTML += detailTemplate(displayInfo);
	sectionInfoTab.innerHTML += directionTemplate(displayInfo, displayInfoImage);
}

const showPreviousImage = (productImages, index) => {
	let visualImage = changeImageOrder(productImages);
	visualImage.classList.add("place_right");
	
	let visualContainer = document.querySelector(".container_visual");
	visualContainer.replaceChild(visualImage, productImages);
	
	setTimeout(() => {
		let newProductImages = visualContainer.firstElementChild;
		newProductImages.classList.add("swipe_right");
		newProductImages.addEventListener("transitionend", () => {
			changeCurrentIndex(index);
		});
	}, 0);
}

const showNextImage = (productImages, index) => {
	if (productImages.classList.contains("swipe_right")) {
		productImages.classList.remove("swipe_right");
	} else if (productImages.classList.contains("swipe_left") === false) {
		productImages.classList.add("swipe_left");
	}
	
	productImages.addEventListener("transitionend", () => {
		let visualImage = changeImageOrder(productImages);
		let visualContainer = document.querySelector(".container_visual");
		visualContainer.replaceChild(visualImage, productImages);

		changeCurrentIndex(index);
	});
}

const swipeProductImage = (event) => {
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

const showProductContent = (event) => {
	let clickedButton = event.currentTarget;
	let productContent = document.querySelector(".store_details");
	let openButton = document.querySelector("._open");
	let closeButton = document.querySelector("._close");
	
	if (clickedButton.classList.contains("_open")) {
		productContent.classList.remove("close3");
		openButton.classList.add("hide");
		openButton.classList.remove("show");
		closeButton.classList.add("show");
		closeButton.classList.remove("hide");
	} else if (clickedButton.classList.contains("_close")) {
		productContent.classList.add("close3");
		openButton.classList.add("show");
		openButton.classList.remove("hide");
		closeButton.classList.add("hide");
		closeButton.classList.remove("show");
	}
}

const showDetail = (event) => {
	let tab = event.target.closest("li");
	let detail = document.querySelector("#detail_tab");
	let direction = document.querySelector("#direction_tab");
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

const showDirection = (event) => {
	let tab = event.target.closest("li");
	let detail = document.querySelector("#detail_tab");
	let direction = document.querySelector("#direction_tab");
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

const registerClickEvent = () => {
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

const requestDatas = () => {
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
    
	let displayInfoId = document.querySelector("#display_info_id").value;
	let url = `/Reservation/api/products/${displayInfoId}`;
	xmlHttpRequest.open("GET", url);
	xmlHttpRequest.send();
}

document.addEventListener("DOMContentLoaded", () => {
    requestDatas();
});
