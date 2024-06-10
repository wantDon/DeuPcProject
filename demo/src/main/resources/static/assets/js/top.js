var intervalId;
var currentIndex = 0; // 현재 배너 인덱스
var banners = [
	'/assets/img/b01.png',
	'/assets/img/b02.png',
	'/assets/img/b03.png',
	'/assets/img/b04.png',
	'/assets/img/b05.png'
];
var totalImages = banners.length;
var loginDate; // 로그인 시간을 저장할 전역 변수
var remainingTimeInSeconds; // 남은 시간을 초 단위로 저장할 변수
var id;

function changeBanner(index) {
	// 현재 배너 숨기기
	document.getElementById('banner').style.display = 'none';
	
	// 다음 배너 인덱스로 이동
	currentIndex = index;
	
	// 다음 배너 표시
	document.getElementById('banner').src = banners[currentIndex];
	document.getElementById('banner').style.display = 'block';
}

function moveLeft() {
	// 좌측으로 이동 (이전 배너)
	currentIndex = (currentIndex - 1 + banners.length) % banners.length;
	changeBanner(currentIndex);
	resetInterval();
}

function moveRight() {
	// 우측으로 이동 (다음 배너)
	currentIndex = (currentIndex + 1) % banners.length;
	changeBanner(currentIndex);
	resetInterval();
}

function resetInterval() {
	// 현재 실행 중인 인터벌을 중지
	clearInterval(intervalId);
	
	// 클릭 동작이 끝난 후 3초 간격으로 인터벌 재설정
	intervalId = setInterval(function() { moveRight(); }, 3000);
}

window.onload = function () {
	getTime();
	intervalId  = setInterval(function() { moveRight() }, 3000);
	setInterval(updateUseTime, 1000); // 매초마다 사용 시간 업데이트

	$('.autoplay').slick({
		slidesToShow: 3,
		slidesToScroll: 1,
		autoplay: true,
		autoplaySpeed: 2000,
	});
}

function getTime() {
	$.ajax({
		url: '/pc/user',
		type: 'POST',
		success: function(response) {
			if (response.error) {
				alert(response.error);
			} else {
				var data = response.split("/");
				id = data[0]; // 아이디
				var loginTime = data[1]; // 로그인 시간
				var remainingTime = data[2]; // 남은 시간

				// 시간을 Date 객체로 변환
				loginDate = new Date(loginTime);
				remainingTimeInSeconds = parseInt(remainingTime) * 60;
				
				// HTML 요소 업데이트
				document.querySelector('.loginId').innerHTML = "로그인 ID : " + id;
				document.querySelector('.leftTime').innerHTML = "남은 시간 : " + remainingTime + "분";

				// 사용 시간 업데이트
				updateUseTime();
			}
		},
		error: function() {
			$('#result').empty();
			alert('서버 요청에 실패했습니다. 잠시 후 다시 시도 해주세요.');
		}
	});
}

function updateUseTime() {
	if (loginDate && remainingTimeInSeconds !== undefined) {
			
		// 현재 시간을 가져옴
		var currentDate = new Date();
		
		// 시간 차이를 밀리초 단위로 계산
		var timeDiff = currentDate - loginDate;
		
		// 밀리초를 시, 분, 초 단위로 변환
		var diffHours = Math.floor(timeDiff / (1000 * 60 * 60));
		var diffMinutes = Math.floor((timeDiff % (1000 * 60 * 60)) / (1000 * 60));
		var diffSeconds = Math.floor((timeDiff % (1000 * 60)) / 1000);
		
		var useTime = diffHours + "시간 " + diffMinutes + "분 " + diffSeconds + "초";
		
		// 사용 시간을 초 단위로 계산
		var usedTimeInSeconds = (diffHours * 3600) + (diffMinutes * 60) + diffSeconds;

		if (remainingTimeInSeconds - usedTimeInSeconds === 600) {
			showNotification("사용 시간 10분 남았습니다.");
		} else if (remainingTimeInSeconds - usedTimeInSeconds === 300) {
			showNotification("사용 시간 5분 남았습니다.");
		}

		// 남은 시간과 사용 시간이 동일한지 확인
		if (usedTimeInSeconds >= remainingTimeInSeconds) {
			window.location.href = '/pc/logout';
		}

		// HTML 요소 업데이트
		document.querySelector('.useTime').innerHTML = "사용 시간 : " + useTime;
	}
}

function charge() {
	if (id.includes('비회원-')) {
		alert('비회원은 시간 충전기능을 이용할 수 없습니다.');
	} else {
		window.location.href = '/timePayment/paymentMain_nonUser?id=' + id;
	}
}

function showNotification(message) {
	var notification = document.createElement('div');
	notification.className = 'notification';
	notification.innerText = message;
	document.body.appendChild(notification);
	
	// 알림 창을 표시
	$(notification).fadeIn();
	
	// 5초 후에 알림 창을 숨기고 제거
	setTimeout(function() {
		$(notification).fadeOut(function() {
			notification.remove();
		});
	}, 5000);
}