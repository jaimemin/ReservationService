const ERROR_STATUS = 400;
const COMPLETE_STATE = 4;

const registerClickEvents = () => {
	let confirmedList = document.querySelector("#confirmed_list");
	let usedList = document.querySelector("#used_list");
	let canceledList = document.querySelector("#canceled_list");
	let confirmedReservations = document.querySelector(".card.confirmed").querySelectorAll(".card_item");
	let logoutButton = document.querySelector(".viewReservation");
	
	confirmedReservations.forEach((reservation) => {
		let cancelButton = reservation.querySelector(".booking_cancel .btn")
		
		cancelButton.addEventListener("click", showDialog);
	});
}

const showDialog = (event) => {
	let dialog = document.querySelector(".popup_booking_wrapper");
	let popupName = dialog.querySelector(".popup_name");
	let reservedDate = dialog.querySelector(".sm");
	let cancelButton = dialog.querySelector(".btn_gray");
	let closeButton = dialog.querySelector(".popup_btn_close");
	let confirmButton = dialog.querySelector(".btn_green");
	let cardBody = event.target.closest(".card_body");
	let reservationId = Number(cardBody.querySelector(".booking_number").innerText.replace(/[^0-9]/g, ""));
	
	popupName.innerText = cardBody.querySelector(".product_description").innerText;
	reservedDate.innerText = cardBody.querySelector(".item_dsc").innerText;
	dialog.classList.remove("hide_dialog");
	dialog.classList.add("show_dialog");
	
	closeButton.addEventListener("click", () => {
		dialog.classList.add("hide_dialog");
		dialog.classList.remove("show_dialog");
	});
	cancelButton.addEventListener("click", () => {
		dialog.classList.add("hide_dialog");
		dialog.classList.remove("show_dialog");
	});
	confirmButton.addEventListener("click", () => {
		dialog.classList.add("hide_dialog");
		dialog.classList.remove("show_dialog");
		
		cancelReservation(reservationId);
	});
}

const cancelReservation = (reservationId) => {
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
	params.id = reservationId;
	params.reservationEmail = document.querySelector("reservation_email").value;
	
	let url = `/Reservation/api/reserve`;
	xmlHttpRequest.open("PUT", url);
	xmlHttpRequest.setRequestHeader("Content-Type", "application/json; charset=utf-8");
	xmlHttpRequest.send(JSON.stringify(params));
}

document.addEventListener("DOMContentLoaded", () => {
	registerClickEvents();
});