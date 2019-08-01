let commentWithoutImageTemplate = (commentInfo) => (
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
					<span class="date">${commentInfo.reservationDate} 방문</span>
				</div>
			</div>
		</div>
    </li>
    `
);

let commentWithImageTemplate = (commentInfo) => (
	`
	<li class="list_item">
		<div>
			<div class="review_area">
				<div class="thumb_area">
					<a href="#" class="thumb" title="이미지 크게 보기"> <img
						width="90" height="90" class="img_vertical_top"
						src="http://127.0.0.1:8080/Reservation/${commentInfo.saveFileName}" alt="리뷰이미지">
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
					<span class="date">${commentInfo.reservationDate} 방문</span>
				</div>
			</div>
		</div>
	</li>
	`
);

let createCommentAverageScore = (averageCommentScore) => {
	let averageCommentScoreTemplate = document.querySelector("#averageCommentScore");
	let starGraph = document.querySelector(".graph_value");

	if(averageCommentScore === null) {
		averageCommentScoreTemplate.innerText = "0.0";
		starGraph.style.width = "0%";
	} else {
		let rating = averageCommentScore.toFixed(1);
		averageCommentScoreTemplate.innerText = rating;
		starGraph.style.width = `${rating * 20}%`;
	}
}

let createCommentCount = (commentList) => {
	let numOfComments = commentList.length;
	let commentCount = document.querySelector(".join_count > .green");
	
	commentCount.innerText = `${numOfComments}건`;
}

let createCommentTemplates = (commentList, displayInfo) => {
	let commentTemplates = [];
	
	if(commentList.length >= 1) {
		commentList.forEach((comment) => {
			let commentInfo = {};
			let email = comment.reservationEmail.substring(0, 4) + "****";
			let date = comment.reservationDate;
			let day = date.dayOfMonth;
			let month = date.monthValue - 1;
			let year = date.year;
			let commentTemplate;

			commentInfo.comment = comment.comment;
			commentInfo.score = `${comment.score}.0`;
			commentInfo.reservationEmail = email;
			commentInfo.reservationDate = `${year} ${month} ${day}`;
			commentInfo.productDescription = displayInfo.productDescription;

			if (comment.commentImages.length >= 1) {
				commentInfo.saveFileName = comment.commentImages[0].saveFileName;

				commentTemplate = commentWithImageTemplate(commentInfo);
			} else {
				commentTemplate = commentWithoutImageTemplate(commentInfo);
			}

			commentTemplates.push(commentTemplate);
		});
	}
	
	return commentTemplates;
}

let createDetailPageComments = (commentList, displayInfo) => {
	let commentTemplates = createCommentTemplates(commentList, displayInfo);
	let reviewUL = document.querySelector(".list_short_review");
	let innerHTML = "";
	
	if(commentTemplates.length === 0) {
		innerHTML = "<li>등록된 댓글이 없습니다</li>";
	}  else if(commentTemplates.length > 3) {
		for(let template = 0; template < 3; template++) {
			innerHTML += commentTemplates[template];
		}
	} else if (commentTemplates.length > 0) {
		commentTemplates.forEach((template) => {
			innerHTML += template;
		});
	}
	
	reviewUL.innerHTML = innerHTML;
}

let createComments = (commentList, displayInfo) => {
	let commentTemplates = createCommentTemplates(commentList, displayInfo);
	let reviewUL = document.querySelector(".list_short_review");
	let innerHTML = "";
	
	if(commentTemplates.length === 0) {
		innerHTML = `<li>등록된 댓글이 없습니다</li>`;
	} else {
		commentTemplates.forEach((template) => {
			innerHTML += template;
		});
	}
	
	reviewUL.innerHTML = innerHTML;
}