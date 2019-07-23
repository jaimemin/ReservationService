const NUMBER_OF_REQUESTS = 4;

let replaceTemplate = (product) => (
	`
	<li class="item">
    	<a href="htmls/detail.html?id=${product.id}" class="item_book">
        	<div class="item_preview">
            	<img alt="${product.description}" class="img_thumb" src="http://127.0.0.1:8080/Reservation/${product.saveFileName}">
				<span class="img_border"></span>
        	</div>
        	<div class="event_txt">
            	<h4 class="event_txt_tit"> 
            		<span>${product.description}</span> 
            		<small class="sm">${product.placeName}</small> 
            	</h4>
				<p class="event_txt_dsc">${product.content}</p>
        	</div>
    	</a>
	</li>
	`
);

let createTemplate = (productList, event) => {
    let list = document.querySelectorAll(".lst_event_box");
    let leftList = list[0];
    let rightList = list[1];

    let index = 0;
    let leftHTML = "";
    let rightHTML = "";
    for(let index = 0; index < productList.length; index++) {
    	if (index % 2 === 0) {
    		leftHTML += replaceTemplate(productList[index]);
    	} else {
    		rightHTML += replaceTemplate(productList[index]);
    	}
    }
    
    let clickedClass = event.target.parentElement.classList;
    
    if (clickedClass.contains("btn") 
    		|| clickedClass.contains("more")) {
    	leftList.innerHTML += leftHTML;
        rightList.innerHTML += rightHTML;
    }
    else if (clickedClass.contains("anchor") 
    		|| clickedClass.contains("active")){
        leftList.innerHTML = leftHTML;
        rightList.innerHTML = rightHTML;
    }
};

let categoryIndex = 0;
let startIndex = 0;
let buttonLimit = 0;

let createCategoryCount = (categoryList, index) => {
    let eventCount = 0;
    if (index >= 1) {
        eventCount = categoryList[index - 1].count;
    } else {
        categoryList.forEach((category) => {
            eventCount += category.count;
        });
    }
    
    let numberOfEvent = document.querySelector(".pink");
    numberOfEvent.innerHTML = eventCount;

    buttonLimit = Math.ceil(eventCount / NUMBER_OF_REQUESTS) - 1;
};

let initializeTotalCount = () => {
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = () => {
    	if(xmlHttpRequest.status >= ERROR_STATUS) {
    		alert("오류가 발생했습니다");
    		return;
    	}
	
    	if(xmlHttpRequest.readyState === READY_STATE) {
    		let eventNum = 0;
    		let categoryList = JSON.parse(xmlHttpRequest.responseText);
    		categoryList.forEach((category) => {
    			eventNum += category.count;
    		});
    	        
    		buttonLimit = Math.ceil(eventNum / NUMBER_OF_REQUESTS) - 1;
    	}
    }

    xmlHttpRequest.open("GET", "/Reservation/api/categories");
    xmlHttpRequest.send();
};

let requestCategories = (categoryIndex) => {
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = () => {
    	if(xmlHttpRequest.status >= ERROR_STATUS) {
    		alert("오류가 발생했습니다");
    		return;
    	}
	
    	if(xmlHttpRequest.readyState === READY_STATE) {
    		let categoryList = JSON.parse(xmlHttpRequest.responseText);
    		createCategoryCount(categoryList, categoryIndex);
    	}
    }

    xmlHttpRequest.open("GET", "/Reservation/api/categories");
    xmlHttpRequest.send();
};

let requestProducts = (event) => {
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = () => {
    	if(xmlHttpRequest.status >= ERROR_STATUS) {
    		alert("오류가 발생했습니다");
    		return;
    	}
	
    	if(xmlHttpRequest.readyState === READY_STATE) {
    		let productList = JSON.parse(xmlHttpRequest.responseText);
            createTemplate(productList, event);
    	}
    }

    let url = "/Reservation/api/products?categoryId=" + categoryIndex + "&start=" + startIndex;
    xmlHttpRequest.open("GET", url);
    xmlHttpRequest.send();
};

let removeMoreButton = (event) => {
	let clickedItem = event.target;
	
    if (clickedItem.classList.contains("btn")) {
        clickedItem.hidden = true;
    } else if (clickedItem.parentElement.classList.contains("btn")) {
        clickedItem.parentElement.hidden = true;
    }
};

let showMoreProducts = (event) => {
    if (event.target.nodeName === "BUTTON" 
    		|| event.target.nodeName === "SPAN") {
        startIndex += NUMBER_OF_REQUESTS;
        requestProducts(event);
        
        if (--buttonLimit === 0) {
            removeMoreButton(event);
        }
    }
};

let categoryListObject = document.querySelector(".anchor");								// 타겟
																						// 기본 값
let categoryList = null;

let loadProducts = (event) => {
    categoryList = event.target.parentElement.parentElement;

    if (categoryList.classList.contains("item")) {
        categoryIndex = categoryList.dataset.category;
        startIndex = 0;

        let removeEffect = () => {
        	categoryListObject.style.color = "black";
        	categoryListObject.style.borderBottom = "none";
        };

        let changeTextToGreen = () => {
            removeEffect();
            categoryListObject = categoryList.firstElementChild;
            categoryListObject.style.color = "#0aba16";
            categoryListObject.style.borderBottom = "2px solid #0aba16";
        };

        requestProducts(event);
        requestCategories(categoryIndex);
        changeTextToGreen();

        let moreButton = document.querySelector(".more");
        moreButton.innerHTML = "<button class='btn'>" +
        		"<span>더보기</span>" +
        		"</button>";
    }
};

let category = document.querySelector(".event_tab_lst");
let moreButton = document.querySelector(".more");
let entireCategory = document.querySelector("#autoClick");

let loadContent = () => {
    category.addEventListener("click", loadProducts);
    moreButton.addEventListener("click", showMoreProducts);
    initializeTotalCount();
};

document.addEventListener("DOMContentLoaded", () => {
    loadContent();
    entireCategory.click();
});
