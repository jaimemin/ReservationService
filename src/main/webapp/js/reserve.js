const ERROR_STATUS = 400;
const COMPLETE_STATE = 4;
const PRICE_TYPE = {
	'A': '성인',
	'Y': '청소년',
	'B': '유아',
	'S': '셋트',
	'D': '장애인',
	'C': '지역주민',
	'E': '얼리버드',
	'V': 'VIP',
	'D': '평일',
	'R': 'R석'
};

let ticketPriceList = [];
let nameValid = false;
let telephoneValid = false;
let emailValid = false;
let agreementChecked = false;
let reserveButtonClicked = false;

const checkEmailValidation = (event) => {
	let email = event.target.value;
	let emailWarningText = document.querySelector(".warning_msg.email");
	emailValid = email.match(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);
	
	if (reserveButtonClicked === false) {
		return;
	}
	
	if (emailValid) {
		emailWarningText.classList.remove("show_warning");
		emailWarningText.classList.add("hide_warning");
	} else {
		emailWarningText.classList.add("show_warning");
		emailWarningText.classList.remove("hide_warning");
	}
}

const checkTelephoneValidation = (event) => {
	let telephone = event.target.value;
	let telephoneWarningText = document.querySelector(".warning_msg.tel");
	telephoneValid = telephone.match(/01[016789]-[0-9]{3,4}-[0-9]{4}/);
	
	if (reserveButtonClicked === false) {
		return;
	}
	
	if (telephoneValid) {
		telephoneWarningText.classList.remove("show_warning");
		telephoneWarningText.classList.add("hide_warning");
	} else {
		telephoneWarningText.classList.add("show_warning");
		telephoneWarningText.classList.remove("hide_warning");
	}
}

const checkNameValidation = (event) => {
	let name = event.target.value;
	let nameWarningText = document.querySelector(".warning_msg.name");
	nameValid = name.match(/.+/g);
	
	if (reserveButtonClicked === false) {
		return;
	}
	
	if (nameValid) {
		nameWarningText.classList.remove("show_warning");
		nameWarningText.classList.add("hide_warning");
	} else {
		nameWarningText.classList.add("show_warning");
		nameWarningText.classList.remove("hide_warning");
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

const clickCheckBox = (event) => {
	let reserveButtonWrap = document.querySelector(".bk_btn_wrap");
	let reserveButton = document.querySelector(".bk_btn");
	
	if (event.target.checked === true) {
		agreementChecked = true;
	} else {
		agreementChecked = false;
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

const checkInputValidation = () => {
	let emailWarningText = document.querySelector(".warning_msg.email");
	let telephoneWarningText = document.querySelector(".warning_msg.tel");
	let nameWarningText = document.querySelector(".warning_msg.name");
	
	if (nameValid) {
		nameWarningText.classList.remove("show_warning");
		nameWarningText.classList.add("hide_warning");
	} else {
		nameWarningText.classList.add("show_warning");
		nameWarningText.classList.remove("hide_warning");
	}
	
	if (emailValid) {
		emailWarningText.classList.remove("show_warning");
		emailWarningText.classList.add("hide_warning");
	} else {
		emailWarningText.classList.add("show_warning");
		emailWarningText.classList.remove("hide_warning");
	}
	
	if (telephoneValid) {
		telephoneWarningText.classList.remove("show_warning");
		telephoneWarningText.classList.add("hide_warning");
	} else {
		telephoneWarningText.classList.add("show_warning");
		telephoneWarningText.classList.remove("hide_warning");
	}

	return nameValid 
		&& emailValid 
		&& telephoneValid;
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
	
	reserveButtonClicked = true;
	if (checkInputValidation() === false) {
		alert("입력한 항목을 다시 확인해주세요.");
		return;
	}
	
	let totalTicketCount = document.querySelector("#totalCount").innerText;
	
	if(totalTicketCount <= 0) {
		alert("티켓을 하나 이상 구매하셔야합니다.");
		return;
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
		setTicketPriceType(ticketPrice);
	}
}

const setTicketPriceType = (ticketPrice) => {
	let ticketPriceType = ticketPrice.querySelector(".product_price_type");
	let key = ticketPriceType.innerText;
	
	ticketPriceType.innerText = PRICE_TYPE[key];
}

const updateReserveButton = () => {
	let reserveButtonWrap = document.querySelector(".bk_btn_wrap");
	let reserveButton = document.querySelector(".bk_btn");

	if (agreementChecked) {
		reserveButtonWrap.classList.remove("disable");
		reserveButton.disabled = false;
	} else {
		reserveButtonWrap.classList.add("disable");
		reserveButton.disabled = true;
	}
}

const registerReserveButtonEvents = () => {
	document.addEventListener("click", updateReserveButton);
}

document.addEventListener("DOMContentLoaded", () => {
	if (document.querySelector(".email").disabled) {
		emailValid = true;
	}
	
	setTicketPrices();
	registerReserveClickEvent();
	registerInputKeyDownEvent();
	registerAgreementClickEvent();
	registerReserveButtonEvents();
});