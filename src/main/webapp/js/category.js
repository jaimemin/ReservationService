let replaceTemplate = (item, product, index) => {
    let resultHTML = item.replace("{id}", product.id)
        .replace("{description}", product.description)
        .replace("{placeName}", product.placeName)
        .replace("{content}", product.content)
        .replace("{productImageUrl}", product.saveFileName);
    return resultHTML;
};

let createTemplate = (productList, event) => {
    let item = document.querySelector("#itemList").innerHTML;
    let list = document.querySelectorAll(".lst_event_box");
    let leftList = list[0];
    let rightList = list[1];

    let index = 0;
    let leftHTML = "";
    let rightHTML = "";
    productList.forEach((product) => {
    	if (index % 2 == 0) {
    		leftHTML += replaceTemplate(item, product, index);
    	} else {
    		rightHTML += replaceTemplate(item, product, index);
    	}
    	
    	index++;
    });
    
    let clickedClass = event.target.parentElement.className;
    
    if (clickedClass === "btn" 
    		|| clickedClass === "more"){
    	leftList.innerHTML += leftHTML;
        rightList.innerHTML += rightHTML;
    }
    else if (clickedClass === "anchor" 
    		|| clickedClass === "anchor active") {
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

    buttonLimit = Math.ceil(eventCount / 4) - 1;
};

let initializeTotalCount = () => {
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = () => {
    	if(xmlHttpRequest.status >= 400) {
    		alert("오류가 발생했습니다");
    		return;
    	}
	
    	if(xmlHttpRequest.readyState === 4) {
    		let eventNum = 0;
    		let categoryList = JSON.parse(xmlHttpRequest.responseText);
    		categoryList.forEach((category) => {
    	        eventNum += category.count;
    	    });
    	        
    	    buttonLimit = Math.ceil(eventNum / 4) - 1;
    	}
    }

    xmlHttpRequest.open("GET", "/Reservation/api/categories");
    xmlHttpRequest.send();
};

let requestCategories = (categoryIndex) => {
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = () => {
    	if(xmlHttpRequest.status >= 400) {
    		alert("오류가 발생했습니다");
    		return;
    	}
	
    	if(xmlHttpRequest.readyState === 4) {
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
    	if(xmlHttpRequest.status >= 400) {
    		alert("오류가 발생했습니다");
    		return;
    	}
	
    	if(xmlHttpRequest.readyState === 4) {
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
	
    if (clickedItem.className === "btn") {
        clickedItem.hidden = true;
    } else if (clickedItem.parentElement.className === "btn") {
        clickedItem.parentElement.hidden = true;
    }
};

let showMoreProducts = (event) => {
    if (event.target.nodeName === "BUTTON" 
    		|| event.target.nodeName === "SPAN") {
        startIndex += 4;
        requestProducts(event);
        
        if (--buttonLimit === 0) {
            removeMoreButton(event);
        }
    }
};

let categoryListObject = document.querySelector(".anchor");    // category 효과 타겟 기본 값
let categoryList = null;

let loadProducts = (event) => {
    categoryList = event.target.parentElement.parentElement;

    if (categoryList.className === "item") {
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

let loadContent = () => {
    category.addEventListener("click", loadProducts);
    moreButton.addEventListener("click", showMoreProducts);
    initializeTotalCount();
};

document.addEventListener("DOMContentLoaded", () => {
    loadContent();
});