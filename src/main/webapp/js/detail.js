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
		previousButton.classList.add("hide");
		nextButton.classList.add("hide");
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

changeImageOrder = (productImages) => {
	productImages.insertAdjacentElement("beforeend", productImages.firstElementChild);

	let visualImage = document.createElement("ul");
	visualImage.classList.add("visual_img", "swipe");
	visualImage.innerHTML = productImages.innerHTML;

	return visualImage;
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

let registerClickEvent = () => {
	let previousButton = document.querySelector(".prev");
	let nextButton = document.querySelector(".nxt");
	
	previousButton.addEventListener("click", swipeProductImage);
	nextButton.addEventListener("click", swipeProductImage);
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
