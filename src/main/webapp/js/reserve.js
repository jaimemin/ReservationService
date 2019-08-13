const ERROR_STATUS = 400;
const COMPLETE_STATE = 4;

let ticketPriceList = [];
let isNameValid = false;
let isTelephoneValid = false;
let isEmailValid = false;
let isAgreementChecked = false;

const checkEmailValidation = (event) => {
	let email = event.target.value;
	let emailWarningText = document.querySelector(".warning_msg.email");
	
	if (email.match(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i)) {
		emailWarningText.classList.remove("show_warning");
		emailWarningText.classList.add("hide_warning");
		
		isEmailValid = true;
	} else {
		emailWarningText.classList.add("show_warning");
		emailWarningText.classList.remove("hide_warning");
		
		isEmailValid = false;
	}
}

const checkTelephoneValidation = (event) => {
	let telephone = event.target.value;
	let telephoneWarningText = document.querySelector(".warning_msg.tel");
	
	if (telephone.match(/^\d{3}-\d{3,4}-\d{4}$/)) {
		telephoneWarningText.classList.remove("show_warning");
		telephoneWarningText.classList.add("hide_warning");
		
		isTelephoneValid = true;
	} else {
		telephoneWarningText.classList.add("show_warning");
		telephoneWarningText.classList.remove("hide_warning");
		
		isTelephoneValid = false;
	}
}

const checkNameValidation = (event) => {
	let name = event.target.value;
	let nameWarningText = document.querySelector(".warning_msg.name");
	
	if (name.match(/.+/g)) {
		nameWarningText.classList.remove("show_warning");
		nameWarningText.classList.add("hide_warning");

		isNameValid = true;
	} else {
		nameWarningText.classList.add("show_warning");
		nameWarningText.classList.remove("hide_warning");

		 isNameValid = false;
	}
}

const registerInputKeyDownEvent = () => {
	let name = document.querySelector(".text");
	let telephone = document.querySelector(".tel");
	let email = document.querySelector(".email");
	
	name.addEventListener("input", checkNameValidation);
	telephone.addEventListener("input", checkTelephoneValidation);
	email.addEventListener("input", checkEmailValidation);
}

const isReserveReady = () => {
	let totalTicketCount = document.querySelector("#totalCount");
	
	return totalTicketCount.innerText > 0
		&& isNameValid 
		&& isTelephoneValid 
		&& isEmailValid
		&& isAgreementChecked;
}

const clickCheckBox = (event) => {
	let reserveButtonWrap = document.querySelector(".bk_btn_wrap");
	let reserveButton = document.querySelector(".bk_btn");
	
	if (event.target.checked === true) {
		isAgreementChecked = true;
	} else {
		isAgreementChecked = false;
	}
}

const showAgreementContent = (event) => {
	let agreementDiv = event.target.closest(".agreement");
	
	agreementDiv.classList.toggle("open");
	agreementDiv.querySelector(".fn").classList.toggle("fn-up2");
	agreementDiv.querySelector(".fn").classList.toggle("fn-down2");
}

const registerAgreementClickEvent = () => {
	let agreementCheckBox = document.querySelector("#chk3");
	let firstAgreementButton = document.querySelectorAll(".btn_agreement")[0];
	let secondAgreementButton = document.querySelectorAll(".btn_agreement")[1];
	
	agreementCheckBox.addEventListener("click", clickCheckBox);
	firstAgreementButton.addEventListener("click", showAgreementContent);
	secondAgreementButton.addEventListener("click", showAgreementContent);
}
	
const reserveTicket = () => {
	let xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = () => {
		if(xmlHttpRequest.status >= ERROR_STATUS) {
			alert("오류가 발생했습니다");
			return;
		}
		
		if(xmlHttpRequest.readyState === COMPLETE_STATE) {
			alert("예약이 완료되었습니다.");
			window.location.href = "/Reservation/my-reservation";
		}
	}

	let params = {};
	params.displayInfoId = document.querySelector("#display_info_id").value;
	params.productId = document.querySelector("#product_id").value;
	params.reservationName = document.querySelector("#name").value;
	params.reservationEmail = document.querySelector("#email").value;
	params.reservationTelephone = document.querySelector("#tel").value;
	params.reservationDate = document.querySelector("#reservation_date").innerText;
	params.reservationPrices = [];
	
	ticketPriceList.forEach((ticketPrice) => {
		let reservationPrice = {
			"productPriceId": ticketPrice.querySelector("#price_id").value,
			"count": Number(ticketPrice.querySelector(".count_control_input").value)
		};

		params.reservationPrices.push(reservationPrice);
	});
	
	let url = `/Reservation/api/reserve`;
	xmlHttpRequest.open("POST", url);
	xmlHttpRequest.setRequestHeader("Content-Type", "application/json; charset=utf-8");
	xmlHttpRequest.send(JSON.stringify(params));
}

const registerReserveClickEvent = () => {
	let reserveButton = document.querySelector(".bk_btn");
		
	reserveButton.addEventListener("click", reserveTicket);
}


const addCommas = (price) => {
	if (typeof price === String) {
		return price.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	} else {
		return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	
}

const removeCommas = (price) => {
	if (typeof price === String) {
		return price.replace(/[^\d]+/g, "");
	} else {
		return price.toString(price).replace(/[^\d]+/g, "");
	}
}

const enableButtons = (ticketPrice) => {
	let minusButton = ticketPrice.querySelector(".btn_plus_minus.ico_minus3");
	let ticketCount = ticketPrice.querySelector(".count_control_input");
	let priceText = ticketPrice.querySelector(".individual_price");
	
	minusButton.classList.remove("disabled");
	minusButton.style.pointerEvents = "";
	ticketCount.classList.remove("disabled");
	priceText.classList.add("on_color");
}

const disableButtons = (ticketPrice) => {
	let minusButton = ticketPrice.querySelector(".btn_plus_minus.ico_minus3");
	let ticketCount = ticketPrice.querySelector(".count_control_input");
	let priceText = ticketPrice.querySelector(".individual_price");
	
	minusButton.classList.add("disabled");
	minusButton.style.pointerEvents = "none";
	ticketCount.classList.add("disabled");
	priceText.classList.remove("on_color");
}

const addTicket = (event) => {
	let ticketPrice = event.target.closest(".qty");
	let ticketCount = ticketPrice.querySelector(".count_control_input");
	let totalPrice = ticketPrice.querySelector(".total_price");
	let price = parseInt(removeCommas(ticketPrice.querySelector(".price").innerText));
	let totalTicketCount = document.querySelector("#totalCount");
	
	ticketCount.value++;
	totalPrice.innerText = addCommas(price * ticketCount.value);
	totalTicketCount.innerText++;

	enableButtons(ticketPrice);
}

const reduceTicket = (event) => {
	let ticketPrice = event.target.closest(".qty");
	let ticketCount = ticketPrice.querySelector(".count_control_input");
	let totalPrice = ticketPrice.querySelector(".total_price");
	let price = parseInt(removeCommas(ticketPrice.querySelector(".price").innerText));
	let totalTicketCount = document.querySelector("#totalCount");
	
	ticketCount.value--;
	totalPrice.innerText = addCommas(price * ticketCount.value);
	totalTicketCount.value--;
	
	if (ticketCount.value === "0") {
		disableButtons(ticketPrice);
	}
}

const registerClickEvents = (ticketPrice) => {
	let plusButton = ticketPrice.querySelector(".btn_plus_minus.ico_plus3");
	let minusButton = ticketPrice.querySelector(".btn_plus_minus.ico_minus3");
	let ticketCount = ticketPrice.querySelector(".count_control_input");

	plusButton.addEventListener("click", addTicket);
	minusButton.addEventListener("click", reduceTicket);

	if(ticketCount === "0") {
		disableButtons(ticketPrice);
	}
}

const setTicketPrices = () => {
	let ticketBody = document.querySelector(".ticket_body");
	let ticketPriceListSize = ticketBody.childElementCount;
	
	for(let index = 0; index < ticketPriceListSize; index++) {
		let ticketPrice = ticketBody.children[index].closest(".qty");
		
		registerClickEvents(ticketPrice);
		ticketPriceList.push(ticketBody.children[index]);
	}
}

const updateReserveButton = () => {
	let reserveButtonWrap = document.querySelector(".bk_btn_wrap");
	let reserveButton = document.querySelector(".bk_btn");
	
	if (isReserveReady()) {
		reserveButtonWrap.classList.remove("disable");
		reserveButton.disabled = false;
	} else {
		reserveButtonWrap.classList.add("disable");
		reserveButton.disabled = true;
	}
}

const registerReserveButtonEvents = () => {
	document.addEventListener("click", updateReserveButton);
	document.addEventListener("input", updateReserveButton);
}

document.addEventListener("DOMContentLoaded", () => {
	setTicketPrices();
	registerReserveClickEvent();
	registerInputKeyDownEvent();
	registerAgreementClickEvent();
	registerReserveButtonEvents();
});