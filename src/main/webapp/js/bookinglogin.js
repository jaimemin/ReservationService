let emailValid = false;
let sendButtonClicked = false;

const registerInputKeyDownEvent = () => {
	let email = document.querySelector(".login_input");
	
	email.addEventListener("input", checkEmailValidation);
}

const checkEmailValidation = (event) => {
	let email = event.target.value;
	let emailWarningText = document.querySelector(".warning_msg.email");
	
	if (email.match(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i)) {
		emailWarningText.classList.remove("show_warning");
		emailWarningText.classList.add("hide_warning");
		
		emailValid = true;
	} else {
		
		if (sendButtonClicked) {
			emailWarningText.classList.add("show_warning");
			emailWarningText.classList.remove("hide_warning");
		}
		
		emailValid = false;
	}
}

const checkFormValidation = (event) => {
	let emailWarningText = document.querySelector(".warning_msg.email");
	
	if (emailValid === false) {
		emailWarningText.classList.add("show_warning");
		emailWarningText.classList.remove("hide_warning");
		
		sendButtonClicked = true;
		alert("이메일 형식에 맞게 입력해주세요.");
		event.preventDefault();
	}
}

document.addEventListener("DOMContentLoaded", () => {
	registerInputKeyDownEvent();
});