const ERROR_STATUS = 400;
const COMPLETE_STATE = 4;

const requestDatas = () => {
	let xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = () => {
		if(xmlHttpRequest.status >= ERROR_STATUS) {
			alert("오류가 발생했습니다");
			return;
		}
	
		if(xmlHttpRequest.readyState === COMPLETE_STATE) {
			let datas = JSON.parse(xmlHttpRequest.responseText);
			
			if (datas.displayInfo === null) {
				alert("조회할 상품이 없습니다.");
				return;
			}
			
			createCommentAverageScore(datas.averageCommentScore);
			createCommentCount(datas.commentsSize);
			createComments(datas.comments, datas.displayInfo);
		}
	}
    
	let displayInfoId = document.querySelector("#display_info_id").value;
	let url = `/Reservation/api/products/${displayInfoId}?is-detail-page=false`;
	xmlHttpRequest.open("GET", url);
	xmlHttpRequest.send();
}

document.addEventListener("DOMContentLoaded", () => {
	requestDatas();
});