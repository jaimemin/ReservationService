// 공통적으로 사용될 ajax 함수
function ajax(url, callback) {
	if (url === null || typeof callback !== 'function') {
		return;
	}
	
	var xmlHttpRequest = new XMLHttpRequest();

	xmlHttpRequest.addEventListener("load", callback);
	xmlHttpRequest.open("GET", url);
	xmlHttpRequest.send();
}

let promotionIndex = 0;
function slidePromotions(promotions) {
	setTimeout(() => {
		let currentIndex = promotionIndex % promotions.length;
		let nextIndex = (promotionIndex + 1) % promotions.length;
		
		promotions[currentIndex].style.transition = "transform 2s ease 0s";
		promotions[nextIndex].style.transition = "transform 2s ease 0s";
		promotions[currentIndex].style.transform = "translateX(-100%)";
		promotions[nextIndex].style.transform = "translateX(0%)";
		// set time delay for transition
		setTimeout(() => {
			promotions[currentIndex].style.transition = "none";
			promotions[nextIndex].style.transform = "translateX(100%)";
			currentIndex = nextIndex;
			slidePromotions(promotions);
		}, 2000);
	}, 2500);
}