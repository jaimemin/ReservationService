const MAX_RATING = 5;
const MIN_TEXT = 5;
const MAX_TEXT = 400;
const ERROR_STATUS = 400;
const COMPLETE_STATE = 4;

let reviewWrite = {};

const registerButtonClickEvent = () => {
	let registerButton = document.querySelector(".box_bk_btn .bk_btn");
	
	registerButton.addEventListener("click", () => {
		if (isRatingValid() === false) {
			alert("오류가 발생했습니다. 별점을 눌러 평가를 해주세요");
			return;
		}
		
		if (isTextLengthValid() === false) {
			alert("오류가 발생했습니다. 글자 수를 확인해주세요");
			return;
		}
		
		let xmlHttpRequest = new XMLHttpRequest();
		xmlHttpRequest.onreadystatechange = () => {
			if(xmlHttpRequest.status >= ERROR_STATUS) {
				alert("오류가 발생했습니다");
				return;
			}
			
			if(xmlHttpRequest.readyState === COMPLETE_STATE) {
				alert("댓글 등록이 완료되었습니다.");
				
				let displayInfoId = document.querySelector("#displayInfoId").value;
				window.location.href = `/Reservation/detail/${displayInfoId}`;
			}
		}
		
		let form = createForm();
		let url = `/Reservation/api/review-write`;
		xmlHttpRequest.open("POST", url);
		xmlHttpRequest.send(form);
	})
}

const createForm = () => {
	let imageFile = document.querySelector(".review_write_footer > #reviewImageFileOpenInput");
	let comment = document.querySelector(".review_textarea").value;
	comment = comment.replace(/\r?\n/g, '<br />');
	let formData = new FormData();
	
	formData.append("score", document.querySelector(".rating > .star_rank").innerText);
	formData.append("comment", comment);
	formData.append("productId", document.querySelector("#productId").value);
	formData.append("reservationInfoId", document.querySelector("#reservationInfoId").value);
	for (let index = 0; index < imageFile.files.length; index++) {
		formData.append("imageFiles[]", imageFile.files[index]);
	}
	
	return formData;
}

const isRatingValid = () => {
	let countSpan = document.querySelector(".rating > .star_rank");
	
	return !countSpan.classList.contains("gray_star");
}

const isTextLengthValid = () => {
	let textLength = document.querySelector(".review_textarea").textLength;
	
	return textLength >= MIN_TEXT && textLength <= MAX_TEXT;
}

const registerImageFileEvents = () => {
	let imageThumbnails = document.querySelector(".lst_thumb");
	let imageInput = document.querySelector(".review_write_footer > #reviewImageFileOpenInput");
	
	imageThumbnails.addEventListener("click", removeThumbnail);
	imageInput.addEventListener("change", addThumbnail);
}

const addThumbnail = () => {
	let imageThumbnails = document.querySelector(".lst_thumb");
	let imageInput = document.querySelector(".review_write_footer > #reviewImageFileOpenInput");
	imageThumbnails.innerHTML = "";
	
	let files = imageInput.files;
	
	if (files.length === 0) {
		return;
	}
	
	Array.from(files).forEach((file) => {
		if (!imageTypeValidation(file)) {
			alert("JPEG, PNG, JPG와 같은 확장자만 가능합니다!");
			return;
		}
		
		let fileReader = new FileReader();
		
		fileReader.readAsDataURL(file);
		fileReader.onload = () => {
			let thumbnail = document.createElement("li");
			thumbnail.innerHTML += thumbnailTemplate();
			
			thumbnail.querySelector(".item_thumb").src = fileReader.result;
			imageThumbnails.innerHTML += thumbnail.innerHTML;
		}
	});
}

const imageTypeValidation = (image) => {
	const flag = (["image/jpeg", "image/png", "image/jpg"].indexOf(image.type) > -1);
	
	return flag;
}

const removeThumbnail = (event) => {
	if (event.target.classList.contains("spr_book")
			|| event.target.classList.contains("ico_del")) {
		let imageInput = document.querySelector(".review_write_footer > #reviewImageFileOpenInput");
		
		let index = indexInList(event.target.closest("li"));
		event.target.closest("li").remove();
		let fileList = new DataTransfer();
		
		let idx = 0;
		Array.from(imageInput.files).forEach((file) => {
			if (idx !== index) {
				fileList.items.add(file);
			}
			
			idx++;
		});
		
		imageInput.files = fileList.files;
		event.stopPropagation();
	}
}

const indexInList = (node) => {
	let children = node.parentNode.childNodes;
	let index = 0;
	
	for (let idx = 0; idx < children.length; idx++) {
		if (children[idx] === node) {
			return index;
		}
		
		if (children[idx].nodeType === 1) {
			index++;
		}
	}
	
	return -1;
}

reviewWrite.ratingStar = {
	ratingArea: document.querySelector(".review_rating > .rating"),
	stars: document.querySelectorAll(".rating > .rating_rdo"),
	countSpan: document.querySelector(".rating > .star_rank"),

	initialize() {
		this.registerRatingStarEvent();
	},

	registerRatingStarEvent() {
		this.ratingArea.addEventListener("click", (event) => {
			if (event.target.type === "checkbox") {
				let rating = event.target.value;
				
				this.fillRating(rating);
				
				if (rating === 0) {
					this.countSpan.classList.add("gray_star");
				} else {
					this.countSpan.classList.remove("gray_star");
				}
			}
		});
	},
	
	fillRating(rating) {
		for (let index = 0; index < rating; index++) {
			this.stars[index].checked = true;
		}
		
		for (let index = rating; index < MAX_RATING; index++) {
			this.stars[index].checked = false;
		}
		
		this.countSpan.innerText = rating;
	}
};

const registerTextAreaEvents = () => {
	let textArea = document.querySelector(".review_textarea");
	let writeInfo = document.querySelector(".review_write_info");
	let textCount = document.querySelector("#text_count");
	
	writeInfo.addEventListener("click", () => {
		writeInfo.style.display = "none";
		textArea.focus();
	});
	
	textArea.addEventListener("input", () => {
		textCount.innerText = textArea.textLength;
	});
	
	textArea.onblur = () => {
		if (textArea.textLength === 0) {
			writeInfo.style.display = "block";
			textArea.focus();
		}
	}
}

document.addEventListener("DOMContentLoaded", () => {
	registerTextAreaEvents();
	reviewWrite.ratingStar.initialize();
	registerImageFileEvents();
	registerButtonClickEvent();
});