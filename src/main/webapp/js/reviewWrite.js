const MAX_RATING = 5;
const MIN_TEXT = 5;
const MAX_TEXT = 400;
const ERROR_STATUS = 400;
const COMPLETE_STATE = 4;

let listIndex = 0;

const registerButtonClickEvent = () => {
	let registerButton = document.querySelector(".box_bk_btn .bk_btn");
	
	registerButton.addEventListener("click", () => {
		if (isCommentValid() === false) {
			alert("오류가 발생했습니다. 글자 수, 별점을 확인해주세요");
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
	let formData = new FormData();
	
	formData.append("score", document.querySelector(".rating > .star_rank").innerText);
	formData.append("comment", document.querySelector(".review_textarea").value);
	formData.append("productId", document.querySelector("#productId").value);
	formData.append("reservationInfoId", document.querySelector("#reservationInfoId").value);
	for (let index = 0; index < imageFile.files.length; index++) {
		formData.append("imageFiles[]", imageFile.files[index]);
	}
	
	return formData;
}

const isCommentValid = () => {
	let textArea = document.querySelector(".review_textarea");
	let countSpan = document.querySelector(".rating > .star_rank");
	let textAreaValid = textArea.textLength >= 5;
	let starValid = !countSpan.classList.contains("gray_star");
	
	return textAreaValid && starValid;
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
			thumbnail.innerHTML += thumbnailTemplate(listIndex++);
			
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

const registerRatingStarEvent = () => {
	let ratingArea = document.querySelector(".review_rating > .rating");
	let stars = document.querySelectorAll(".rating > .rating_rdo");
	let countSpan = document.querySelector(".rating > .star_rank");
	
	ratingArea.addEventListener("click", (event) => {
		if (event.target.type === "checkbox") {
			let rating = event.target.value;
			
			for (let index = 0; index < rating; index++) {
				stars[index].checked = true;
			}
			
			for (let index = rating; index < MAX_RATING; index++) {
				stars[index].checked = false;
			}
			
			countSpan.innerText = rating;
			
			if (rating === 0) {
				countSpan.classList.add("gray_star");
			} else {
				countSpan.classList.remove("gray_star");
			}
		}
	});
}

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

const limitTextArea = () => {
	let textArea = document.querySelector(".review_textarea");
	
	textArea.minLength = MIN_TEXT;
	textArea.maxLength = MAX_TEXT;
}

document.addEventListener("DOMContentLoaded", () => {
	limitTextArea();
	registerTextAreaEvents();
	registerRatingStarEvent();
	registerImageFileEvents();
	registerButtonClickEvent();
});