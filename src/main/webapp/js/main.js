const ERROR_STATUS = 400;
const COMPLETE_STATE = 4;

let promotionImageUrl = [];

const initiateSliding = () => {
	const replaceTemplate = (imageUrl) => {
		const promotion = document.querySelector("#promotion_item").innerHTML;

		return promotion.replace("{productImageUrl}", imageUrl);
	};

	const createTemplate = () => {
		let visualImage = document.createElement("ul");
		visualImage.classList.add("visual_img");

		let resultHTML = "";
		promotionImageUrl.forEach((url) => {
			resultHTML += replaceTemplate(url);
		});

		visualImage.innerHTML = resultHTML;

		let containerVisual = document.querySelector(".visual_img").parentElement;
		containerVisual.replaceChild(visualImage, document.querySelector(".visual_img"));
	};

	const changeOrder = () => {
		let unorderedList = document.querySelector(".visual_img");
		let firstChild = unorderedList.firstElementChild;
		unorderedList.insertAdjacentElement("beforeend", firstChild);

		let visualImage = document.createElement("ul");
		visualImage.classList.add("visual_img");
		visualImage.innerHTML = unorderedList.innerHTML;

		let visualContainer = document.querySelector(".visual_img").parentElement;
		visualContainer.replaceChild(visualImage, document.querySelector(".visual_img"));
	};

	const createSlidingClass = () => {
		let visualImage = document.querySelector(".visual_img");
		visualImage.classList.add("sliding");
	};

	const slidePromotion = () => {
		setTimeout(() => {
			createSlidingClass();
			addEventListener("transitionend", changeOrder);
			slidePromotion();
		}, 2000);
	};

	let xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = () => {
		if(xmlHttpRequest.status >= ERROR_STATUS) {
			alert("오류가 발생했습니다");
			return;
		}
    	
		if(xmlHttpRequest.readyState === COMPLETE_STATE) {
			let imageList = JSON.parse(xmlHttpRequest.responseText);
			imageList.forEach((image) => {
				promotionImageUrl.push(image.saveFileName);
			});

			createTemplate();
			slidePromotion();
		}
	}

	xmlHttpRequest.open("GET", "/Reservation/api/promotions");
	xmlHttpRequest.send();
};

document.addEventListener("DOMContentLoaded", () => {
	initiateSliding();
});