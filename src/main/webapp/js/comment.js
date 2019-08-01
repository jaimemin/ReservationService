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
