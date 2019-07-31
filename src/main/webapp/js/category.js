let replaceTemplate = (product) => (
	`
	<li class="item">
    	<a href="/Reservation/detail/${product.displayInfoId}" class="item_book">
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

    let leftHTML = "";
    let rightHTML = "";
    for(let index = 0; index < productList.length; index++) {
    	let product = productList[index];
    	
    	if (index % 2 === 0) {
    		leftHTML += replaceTemplate(product);
    	} else {
    		rightHTML += replaceTemplate(product);
    	}
    }
    
    let clickedClass = event.target.parentElement.classList 
    	|| event.target.classList;

    if (clickedClass.contains("btn") 
    		|| clickedClass.contains("more")) {
    	leftList.innerHTML += leftHTML;
        rightList.innerHTML += rightHTML;
    }
    else if (clickedClass.contains("anchor") 
    		|| clickedClass.contains("active")
    		|| clickedClass.contains("item")) {
        leftList.innerHTML = leftHTML;
        rightList.innerHTML = rightHTML;
    }
};

let categoryIndex = 0;
let startIndex = 0;
let buttonLimit = 0;

let setCategoryCount = (categoryList, index) => {
    let eventCount = 0;
    
    if (index >= 1) {
        eventCount = categoryList[index - 1].categoryCount;
    } else {
        categoryList.forEach((category) => {
            eventCount += category.categoryCount;
        });
    }
    
    let numberOfEvent = document.querySelector(".pink");
    numberOfEvent.innerHTML = `${eventCount}개`;

    buttonLimit = Math.ceil(eventCount / NUMBERS_PER_REQUEST) - 1;
};

let initializeButtonLimit = () => {
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = () => {
    	if(xmlHttpRequest.status >= ERROR_STATUS) {
    		alert("오류가 발생했습니다");
    		return;
    	}
	
    	if(xmlHttpRequest.readyState === COMPLETE_STATE) {
    		let eventCount = 0;
    		let categoryList = JSON.parse(xmlHttpRequest.responseText);
    		categoryList.forEach((category) => {
    			eventCount += category.count;
    		});
    	        
    		buttonLimit = Math.ceil(eventCount / NUMBERS_PER_REQUEST) - 1;
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
	
    	if(xmlHttpRequest.readyState === COMPLETE_STATE) {
    		let categoryList = JSON.parse(xmlHttpRequest.responseText);
    		setCategoryCount(categoryList, categoryIndex);
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
	
    	if(xmlHttpRequest.readyState === COMPLETE_STATE) {
    		let productList = JSON.parse(xmlHttpRequest.responseText);
            createTemplate(productList, event);
    	}
    }

    let url = `/Reservation/api/products?categoryId=${categoryIndex}&start=${startIndex}`;
    xmlHttpRequest.open("GET", url);
    xmlHttpRequest.send();
};

let removeMoreButton = (event) => {
	let clickedItem = event.target;
	let clickedItemParent = clickedItem.parentElement;
	
    if (clickedItem.classList.contains("btn")) {
        clickedItem.hidden = true;
    } else if (clickedItemParent.classList.contains("btn")) {
        clickedItem.parentElement.hidden = true;
    }
};

let showMoreProducts = (event) => {
    if (event.target.nodeName === "BUTTON" 
    		|| event.target.nodeName === "SPAN") {
        startIndex += NUMBERS_PER_REQUEST;
        requestProducts(event);
        
        if (--buttonLimit === 0) {
            removeMoreButton(event);
        }
    }
};

let clickedCategory = document.querySelector(".anchor");
let category = null;

let loadProducts = (event) => {
	category = event.target.closest("li");
	
    if (category.classList.contains("item")) {
        categoryIndex = category.dataset.category;
        startIndex = 0;

        let removeEffect = () => {
        	clickedCategory.classList.remove("highlighted");
        	clickedCategory.classList.add("plain");
        };

        let changeTextToGreen = () => {
            removeEffect();
            clickedCategory = category.firstElementChild;
            clickedCategory.classList.remove("plain");
            clickedCategory.classList.add("highlighted");
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

let categoryList = document.querySelector(".event_tab_lst");
let moreButton = document.querySelector(".more");
let entireCategory = document.querySelector("#autoClick");

let loadContent = () => {
    categoryList.addEventListener("click", loadProducts);
    moreButton.addEventListener("click", showMoreProducts);
    initializeButtonLimit();
};

document.addEventListener("DOMContentLoaded", () => {
    loadContent();
    entireCategory.click();
});
