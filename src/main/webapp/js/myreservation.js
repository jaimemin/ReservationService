const ERROR_STATUS = 400;
const COMPLETE_STATE = 4;

const registerClickEvents = () => {
	let confirmedReservations = document.querySelector(".card.confirmed").querySelectorAll(".card_item");
	
	confirmedReservations.forEach((reservation) => {
		let cancelButton = reservation.querySelector(".booking_cancel .btn")
		
		cancelButton.addEventListener("click", cancelReservation);
	});
}

const cancelReservation = () => {
	let xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = () => {
		if(xmlHttpRequest.status >= ERROR_STATUS) {
			alert("오류가 발생했습니다");
			return;
		}
	
		if(xmlHttpRequest.readyState === COMPLETE_STATE) {
			alert("예약이 취소되었습니다.");
			window.location.href = "/Reservation/my-reservation";
		}
	}

	let params = {};
	let reservationId = Number(document.querySelector(".booking_number").innerText.replace(/[^0-9]/g, ""));
	params.id = reservationId;
	
	let url = `/Reservation/api/reserve`;
	xmlHttpRequest.open("PUT", url);
	xmlHttpRequest.setRequestHeader("Content-Type", "application/json; charset=utf-8");
	xmlHttpRequest.send(JSON.stringify(params));
}

document.addEventListener("DOMContentLoaded", () => {
	registerClickEvents();
});