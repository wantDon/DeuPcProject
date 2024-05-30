





document.querySelectorAll('.btn-primary').forEach(button => {

    button.addEventListener('click', () => {
        console.log('Button was clicked!');
    });

    
});

document.addEventListener("DOMContentLoaded", function() {
    var addItemButton = document.getElementById("addItemInBucket");
    var timeItem = document.getElementById("timeItem1");
    var buyItemList = document.getElementById("buyItemList");
    var allPrice = document.getElementById("allPrice");
    var allTime = document.getElementById("allTime");
    var cancelButton = document.getElementById("cancleButton");

    function updateTotalPrice() {
        var total = 0;
        var items = buyItemList.getElementsByClassName("list-group-item");
        for (var i = 0; i < items.length; i++) {
            var itemPrice = parseInt(items[i].getAttribute("data-price"));
            total += itemPrice;
        }
        allPrice.textContent = "합계 금액 " + total + "원";
    }

    function updateTotalTime() {
        var totalMinutes = 0;
        var items = buyItemList.getElementsByClassName("list-group-item");
        for (var i = 0; i < items.length; i++) {
            var itemTime = parseInt(items[i].getAttribute("data-time"));
            totalMinutes += itemTime;
        }

        var hours = Math.floor(totalMinutes / 60);
        var minutes = totalMinutes % 60;
        allTime.textContent = "충전시간 " + hours + "시간 " + minutes + "분";
    }

    addItemButton.addEventListener("click", function() {
        var itemName = timeItem.querySelector("#itemName").textContent;
        var itemPrice = timeItem.querySelector("#itemPrice").textContent;
        var itemTime = timeItem.getAttribute("data-time");
        var itemPriceValue = timeItem.getAttribute("data-price");

        var listItem = document.createElement("li");
        listItem.className = "list-group-item";
        listItem.setAttribute("data-name", itemName);
        listItem.setAttribute("data-price", itemPriceValue);
        listItem.setAttribute("data-time", itemTime);
        listItem.setAttribute("data-itemNum", "1");

        listItem.innerHTML = `
            <label class="form-check-label">${itemName}</label>
            <label class="form-check-label">${itemPrice}</label>
            <label class="form-check-label">1개</label>
            <button class="btn btn-primary item-more-add-button" type="submit">+</button>
            <button class="btn btn-primary item-more-reduce-button" type="submit">-</button>
        `;

        buyItemList.appendChild(listItem);

        // Add event listener to the newly added reduce button
        var reduceButton = listItem.querySelector(".item-more-reduce-button");
        reduceButton.addEventListener("click", function() {
            listItem.remove();
            updateTotalPrice();
            updateTotalTime();
        });

        // Update total price and time
        updateTotalPrice();
        updateTotalTime();
    });

    // Add event listener to the initial reduce button
    var initialReduceButton = document.getElementById("itemMoreReduceButton");
    var initialBuyItem = document.getElementById("buyItem");
    initialReduceButton.addEventListener("click", function() {
        initialBuyItem.remove();
        updateTotalPrice();
        updateTotalTime();
    });

    // Cancel button event listener
    cancelButton.addEventListener("click", function() {
        // Remove all buyItem elements
        while (buyItemList.firstChild) {
            buyItemList.removeChild(buyItemList.firstChild);
        }
        // Update total price and time
        updateTotalPrice();
        updateTotalTime();
    });

    // Initial calculation of the total price and time
    updateTotalPrice();
    updateTotalTime();
});