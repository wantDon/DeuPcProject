document.addEventListener('DOMContentLoaded', function() {
	document.getElementById("newPriceMenuButton").addEventListener("click", function() {
		var successUrl = '/priceListManagement/priceListManagement/newTimeMenu';
		window.location.href = successUrl;
	});
            
	document.getElementById("deletePriceMenu").addEventListener("click", function() {
		var successUrl = '/priceListManagement/priceListManagement/deleteTimeMenu?timeNum=' + encodeURIComponent(document.getElementById('timeNumInput').value);
		window.location.href = successUrl; // 생성된 URL로 이동
	});

	document.getElementById("refixPriceMenu").addEventListener("click", function() {
		var memberSwitch = document.getElementById('memberSwitch');
		var switch_value;
		
		if(memberSwitch.checked==true) switch_value = 0;
		else switch_value = 1;
		
		var successUrl = '/priceListManagement/priceListManagement/refixTimeMenu?timeNum='
			 + encodeURIComponent(document.getElementById('timeNumInput').value)
			 + '&time_div='+ encodeURIComponent(switch_value)
			 + '&time_use='+ encodeURIComponent(document.getElementById('timeInput').value)
			 + '&time_price='+ encodeURIComponent(document.getElementById('priceInput').value);
		window.location.href = successUrl; // 생성된 URL로 이동
	});

	// 모든 요금제 카드 요소를 선택합니다.
	var cards = document.querySelectorAll('.card');

	cards.forEach(function(card) {
		var timeDiv = card.getAttribute('data-time_div');
		var timeDivElement = card.querySelector('.card-title:nth-child(2)');
		
		if (timeDivElement) {
			if (timeDiv === '0') {
				timeDivElement.innerText = `회원`;
			} else {
				timeDivElement.innerText = `비회원`;
			}
		}
	
	    // time_use 값을 시간과 분으로 나누어서 출력합니다.
	    var timeUse = card.getAttribute('data-time');
	    var hours = Math.floor(timeUse / 60);
	    var minutes = timeUse % 60;
		var timeUseElement = card.querySelector('.card-title:nth-child(3)');
		
		if (timeUseElement) {
			timeUseElement.innerText = hours + '시간 ' + minutes + '분';
		}
	
	    var button = card.querySelector('.btn.btn-primary[type="submit"]');
		if (button) {
			// 해당 버튼에 이벤트 리스너를 추가합니다.
			button.addEventListener('click', function() {
				// 선택된 요금제 정보를 가져옵니다.
				var num = card.getAttribute("data-time_num")
				var time = card.getAttribute('data-time');
				var price = card.getAttribute('data-price');
				var timeDiv = card.getAttribute('data-time_div');
				
				// 입력 필드에 값 설정
				document.getElementById('timeNumInput').value = num;
				document.getElementById('timeInput').value = time;
				document.getElementById('priceInput').value = price;
				
				// 스위치 조정
				var memberSwitch = document.getElementById('memberSwitch');
				memberSwitch.checked = (timeDiv === "0");
			});
		}
	});
});
