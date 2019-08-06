const createCommentAverageScore = (averageCommentScore) => {
	let averageCommentScoreTemplate = document.querySelector("#average_comment_score");
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

const createCommentCount = (commentList) => {
	let numOfComments = commentList.length;
	let commentCount = document.querySelector(".join_count > .green");
	
	commentCount.innerText = `${numOfComments}건`;
}

const createCommentTemplates = (commentList, displayInfo) => {
	let commentTemplates = [];
	
	commentList.forEach((comment) => {
		let commentInfo = {};
		let commentTemplate;

		commentInfo.comment = comment.comment;
		commentInfo.score = `${comment.score}.0`;
		commentInfo.reservationEmail = comment.reservationEmail;
		commentInfo.createdDate = comment.createdDate;
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

const createDetailPageComments = (commentList, displayInfo) => {
	let commentTemplates = createCommentTemplates(commentList, displayInfo);
	let reviewUL = document.querySelector(".list_short_review");
	let innerHTML = "";
	
	if(commentTemplates.length === 0) {
		innerHTML = `<li>등록된 댓글이 없습니다</li>`;
	} else if (commentTemplates.length > 3) {
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

const createComments = (commentList, displayInfo) => {
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