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
	intervalId  = setInterval(function() { moveRight() }, 3000);
}