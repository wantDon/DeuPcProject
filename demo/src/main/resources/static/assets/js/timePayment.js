document.addEventListener("DOMContentLoaded", function() {
    // 추가 버튼에 대한 클릭 이벤트 처리
    document.querySelectorAll('[id^="addItemInBucket-"]').forEach(addButton => {
        addButton.addEventListener("click", function() {
            var timeNum = this.id.split("-")[1]; // id에서 timeNum을 추출
            var timeItem = document.getElementById("timeItem-" + timeNum); // 해당 timeNum을 가진 timeItem을 가져옴
            var itemName = timeItem.querySelector("#itemName-" + timeNum)?.textContent; // 수정된 부분
            var itemPrice = timeItem.querySelector("#itemPrice-" + timeNum)?.textContent; // 수정된 부분
            var itemTime = timeItem.getAttribute("data-time");
            var itemPriceValue = timeItem.getAttribute("data-price");
            console.log(itemTime);

            var buyItemList = document.getElementById("buyItemList");

            // 동일한 아이템이 있는지 확인
            var existingItem = Array.from(buyItemList.children).find(item => item.getAttribute("data-name") === itemName);

            if (existingItem) {
                // 동일한 아이템이 있으면 개수만 증가
                var itemNum = parseInt(existingItem.getAttribute("data-itemNum")) + 1;
                existingItem.setAttribute("data-itemNum", itemNum.toString());
                existingItem.querySelector('.form-check-label:nth-of-type(3)').textContent = itemNum + '개';
            } else {
                // 동일한 아이템이 없으면 새로운 항목 추가
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
            }

            // Update total price and time
            updateTotalPrice();
            updateTotalTime();
        });

    });

    // 결제 정보 확인 버튼 클릭 이벤트 처리======================================================
    function requestPay() {
        // 필요한 정보를 찾는다.
        var allPriceElement = document.getElementById("allPrice");
        var allTimeElement = document.getElementById("allTime");

        // 가격과 시간을 추출하여 숫자 형식으로 변환
        var allPriceText = allPriceElement.textContent;
        var allTimeText = allTimeElement.textContent;

        var priceMatch = allPriceText.match(/(\d+)원/); // "합계 금액 1000원"에서 1000을 추출
        var timeMatch = allTimeText.match(/(\d+)시간 (\d+)분/); // "충전시간 1시간 30분"에서 1시간 30분을 추출

        var price = priceMatch ? parseInt(priceMatch[1]) : 0;
        var hours = timeMatch ? parseInt(timeMatch[1]) : 0;
        var minutes = timeMatch ? parseInt(timeMatch[2]) : 0;
        var totalTime = (hours * 60) + minutes; // 전체 시간을 분 단위로 변환

        var user = document.getElementById("userinfo");
        console.log(user.dataset.id); // data-id 속성 값 출력

       //IMP.request_pay(param, callback)
       IMP.init('imp05733874');
       IMP.request_pay({ //param
          pg: "html5_inicis",
          pay_method: "card",
          merchant_uid: createOrderNum(), //주문번호 생성(하단)
          name: totalTime + "분 충전", // 주문명 (예: "90분 충전")
          amount: 100, // 가격 (예: 1000)
          buyer_email: "aaa@naver.com", //메일
          buyer_name: user.dataset.userId, //사용자 id 입력
          buyer_tel: "01001000100 ", //전화번호
          buyer_addr: "부산광역시 진구", //주소
          buyer_postcode: "2" //우편번호
          }, function (rsp) { //callback
          if (rsp.success) {
             //결제성공시 로직
            alert('결제가 성공적으로 완료되었습니다.');
            var successUrl = '/timePayment/paymentMain/payItem?price=' + encodeURIComponent(price) + '&times=' + encodeURIComponent(totalTime) + '&userId='+encodeURIComponent(user.dataset.id);
            console.log(user.dataset.id +"asdf");
            window.location.href = successUrl; // 생성된 URL로 이동
          } else { //결제 실패
             alert("결재 실패\n" + rsp.error_msg);
          }
       });
       console.log(IMP.request_pay.merchant_uid);

    }

    //주문번호 만들기
    function createOrderNum() {
       const date = new Date();
       const year = date.getFullYear();
       const month = String(date.getMonth() + 1).padStart(2, "0");
       const day = String(date.getDate()).padStart(2, "0");
       let orderNum = year + month + day;
       for(let i=0;i<10;i++) {
          orderNum += Math.floor(Math.random() * 8);
       }
       return orderNum;
    }
    document.getElementById("buyButton").addEventListener("click",requestPay);

    // 초기 구매 항목에 대한 감소 버튼에 대한 클릭 이벤트 처리========================================
    var initialReduceButton = document.getElementById("itemMoreReduceButton");
    var initialBuyItem = document.getElementById("buyItem");
    initialReduceButton.addEventListener("click", function() {
        initialBuyItem.remove();
        updateTotalPrice();
        updateTotalTime();
    });

    // 취소 버튼에 대한 클릭 이벤트 처리
    var cancelButton = document.getElementById("cancleButton");
    cancelButton.addEventListener("click", function() {
        var buyItemList = document.getElementById("buyItemList");
        // Remove all buyItem elements
        while (buyItemList.firstChild) {
            buyItemList.removeChild(buyItemList.firstChild);
        }
        // Update total price and time
        updateTotalPrice();
        updateTotalTime();
    });

    // Total price와 Total time을 업데이트하는 함수
    function updateTotalPrice() {
        var total = 0;
        var items = document.querySelectorAll("#buyItemList > .list-group-item");
        items.forEach(item => {
            var itemPrice = parseInt(item.getAttribute("data-price"));
            console.log(itemPrice);
            console.log('price');
            var itemNum = parseInt(item.getAttribute("data-itemNum"));
            console.log(itemNum);

            total += itemPrice * itemNum;
        });
        var allPrice = document.getElementById("allPrice");
        allPrice.textContent = "합계 금액 " + total + "원";
    }

    function updateTotalTime() {
        var totalMinutes = 0;
        var items = document.querySelectorAll("#buyItemList > .list-group-item");
        items.forEach(item => {
            var itemTime = parseInt(item.getAttribute("data-time"));
            var itemNum = parseInt(item.getAttribute("data-itemNum"));
            totalMinutes += itemTime * itemNum;
        });

        var hours = Math.floor(totalMinutes / 60);
        var minutes = totalMinutes % 60;
        var allTime = document.getElementById("allTime");
        allTime.textContent = "충전시간 " + hours + "시간 " + minutes + "분";
    }

    // 초기화
    updateTotalPrice();
    updateTotalTime();
});
//결제 코드



