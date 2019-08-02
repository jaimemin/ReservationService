const requestDatas = () => {
	let xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = () => {
		if(xmlHttpRequest.status >= ERROR_STATUS) {
			alert("오류가 발생했습니다");
			return;
		}
	
		if(xmlHttpRequest.readyState === COMPLETE_STATE) {
			let datas = JSON.parse(xmlHttpRequest.responseText);
			
			createCommentAverageScore(datas.averageCommentScore);
			createCommentCount(datas.comments);
			createComments(datas.comments, datas.displayInfo);
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
