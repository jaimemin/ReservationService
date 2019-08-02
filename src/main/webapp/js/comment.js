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
	
	commentList.forEach((comment) => {
		let commentInfo = {};
		let email = comment.reservationEmail.substring(0, 4) + "****";
		let date = comment.createdDate;
		let day = date.dayOfMonth;
		let month = date.monthValue - 1;
		let year = date.year;
		let commentTemplate;

		commentInfo.comment = comment.comment;
		commentInfo.score = `${comment.score}.0`;
		commentInfo.reservationEmail = email;
		commentInfo.createdDate = `${year} ${month} ${day}`;
		commentInfo.productDescription = displayInfo.productDescription;

		if (comment.commentImages.length >= 1) {
			commentInfo.saveFileName = comment.commentImages[0].saveFileName;

			commentTemplate = commentWithImageTemplate(commentInfo);
		} else {
			commentTemplate = commentWithoutImageTemplate(commentInfo);
		}
			
		commentTemplates.push(commentTemplate);
	});
	
	return commentTemplates;
}

let createDetailPageComments = (commentList, displayInfo) => {
	let commentTemplates = createCommentTemplates(commentList, displayInfo);
	let reviewUL = document.querySelector(".list_short_review");
	let innerHTML = "";
	
	if(commentTemplates.length === 0) {
		innerHTML = `<li>등록된 댓글이 없습니다</li>`;
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