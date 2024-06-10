var currentElement = null;
var use = 0;
var timerIDs = {};
var seconds = 0;

function formatBirth(birth) {
	return birth.replace(/(\d{4})(\d{2})(\d{2})/, '$1년 $2월 $3일');
}

function formatTime(time) {
	const hours = Math.floor(time / 60);
	const minutes = time % 60;
	return `${hours}시간 ${minutes}분`;
}

function formatGrade(grade) {
	if (grade == 0) {
		return `관리자`;
	} else if (grade == 1) {
		return `일반회원`;
	} else if (grade == 2) {
		return `블랙리스트`;
	} else {
		return `Error`;
	}
}

function formatStartTime(startTime) {
	const [hours, minutes] = startTime.split(':');
	return `${hours}시 ${minutes}분`;
}

function seat(num) {
	const spanText = document.getElementById("spc" + num).innerHTML;
	
	if (!spanText.includes(":")) return;
	
	var element = document.getElementById("pc" + num);
	currentElement = element;
	
	if (element.classList.contains("active")) {
		element.classList.remove("active");
		element.style.backgroundColor = "#ddd"
	} else {
		const userId = spanText.split("<br>")[1];
		
		$.ajax({
			url: '/pc/admin/info/' + userId,
			type: 'POST',
			success: function(response) {
				$('#seatDetails').empty();
				
				if (response.error) {
					alert(response.error);
				} else {
					const memberInfo = response.memberInfo;
					if (memberInfo.id.includes("비회원")) {
						const time = formatTime(memberInfo.time.toString());
						const resultHtml = `<br>
							아이디 : ${memberInfo.id}<br>
							남은시간 : ${time}<br>
						`;
						$('#seatDetails').append(resultHtml);
					} else {
						const birth = formatBirth(memberInfo.birth);
						const time = formatTime(memberInfo.time.toString());
						const grade = formatGrade(memberInfo.grade.toString());
						const resultHtml = `<br>
							아이디 : ${memberInfo.id}<br>
							이름 : ${memberInfo.name}<br>
							이메일 : ${memberInfo.email}<br>
							생년월일 : ${birth}<br>
							휴대전화 : ${memberInfo.phone}<br>
							남은시간 : ${time}<br>
							회원등급 : ${grade}<br>
						`;
						$('#seatDetails').append(resultHtml);
					}
					$('#seatModal').data('seat-id', memberInfo.id);
					
					element.classList.add("active");
					element.style.backgroundColor = "#FFF"
					$('#seatModal').modal('show');
				}
			},
			error: function() {
				$('#result').empty();
				alert('서버 요청에 실패했습니다. 잠시 후 다시 시도 해주세요.');
			}
		});
	}
}

function getUser() {
	// AJAX 요청 생성
	var xhr = new XMLHttpRequest();
	xhr.open('GET', '/pc/admin/ulist', true);
	
	// 요청 완료 시 실행될 함수
	xhr.onload = function() {
		if (xhr.status === 200) {
			// 서버에서 받아온 데이터
			var ulist = JSON.parse(xhr.responseText);
			
			// 리스트를 출력하는 함수 호출
			getAllUser(ulist);
		} else {
			console.error('서버 요청 실패: ' + xhr.status);
		}
	};

	// 요청 보내기
	xhr.send();
}

$(document).ready(function() {
    $(document).on("click", ".admin-modal-btn.btn-danger", function() {
        const userId = $(this).closest('.modal').data('seat-id');
        $.ajax({
			url: '/pc/logout2/' + userId,
			type: 'POST',
			success: function(response) {
				if (response.error) {
					alert(response.error);
				} else {
					alert("해당 PC는 로그아웃 되었습니다.")
				}
			},
			error: function() {
				alert('서버 요청에 실패했습니다. 잠시 후 다시 시도 해주세요.');
			}
		});
    });
	
	// 페이지가 로드될 때 실행되는 함수
	window.onload = function() { 
		generatePCSeats();
	
		getUser();
	};
	
	$('#seatModal').on('hidden.bs.modal', function () {
		if (currentElement) {
			currentElement.style.backgroundColor = "#ddd";
			currentElement.classList.remove("active");
			currentElement = null;
		}
	});
	
	var socket = new SockJS('/ws'); // WebSocket 서버 URL
	var stompClient = Stomp.over(socket);
	
	stompClient.connect({}, function(){
		stompClient.subscribe('/topic/login', function(message) {
			if (message.body.includes("logout")) {
				var id = message.body.split("/")[1];
				var pc = message.body.split("/")[2];
				
				document.getElementById("spc" + pc).innerHTML = "";
				document.getElementById("dpc-" + pc).style.display = "none";
				clearInterval(timerIDs[pc]);
				use -= 1;
			} else {
				var id = message.body.split("/")[0];
				var pc = message.body.split("/")[1];
				var time = message.body.split("/")[2];
				var start = "시작 시간 : " + formatStartTime(time.split(" ")[1]);
				
				document.getElementById("dpc-" + pc).style.display = "block";
				
				time = new Date(time);
				currentTime = new Date();
				timeDifference = currentTime - time;
				
				seconds = Math.floor((timeDifference / 1000) % 60);
				var minutes = Math.floor((timeDifference / 1000 / 60) % 60);
				var hours = Math.floor((timeDifference / (1000 * 60 * 60)) % 24);
				var days = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
				
				if (minutes < 10) minutes = '0' + minutes;
				if (hours < 10) hours = '0' + hours;
				if (days < 10) days = '0' + days;
				
				var times = "";
				
				if (days >= 1) times = days +  " : " + hours + " : " + minutes;
				else times = hours + " : " + minutes;
				
				document.getElementById("spc" + pc).innerHTML = "<br>" + id + "<br>" + times + "<br>" + start;
				
				var time = message.body.split("/")[2];
				time = time.replace(" ", "T");
				
				timer(id, pc, time);
				use += 1;
			}
		});
	});
});

function timer(id, pc, startTime) {	
	var currentTime = new Date();
	var time = new Date(startTime);
	var timeDifference = currentTime - time;
	
	seconds = Math.floor((timeDifference / 1000) % 60);
	
	function updateTimer() {
		currentTime = new Date();
		timeDifference = currentTime - time;
		
		seconds = Math.floor((timeDifference / 1000) % 60);
		var minutes = Math.floor((timeDifference / 1000 / 60) % 60);
		var hours = Math.floor((timeDifference / (1000 * 60 * 60)) % 24);
		var days = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
		
		if (minutes < 10) minutes = '0' + minutes;
		if (hours < 10) hours = '0' + hours;
		if (days < 10) days = '0' + days;
		
		var times = "";
		
		if (days >= 1) times = days +  " : " + hours + " : " + minutes;
		else times = hours + " : " + minutes;
		
		var start = "시작 시간 : " + formatStartTime(startTime.split("T")[1]);
		
		document.getElementById("spc" + pc).innerHTML = "<br>" + id + "<br>" + times + "<br>" + start;
	}

	// 처음 업데이트를 1분 단위에 맞추기 위해 필요한 시간을 계산
	var initialDelay = 0;
	
	if (time.getSeconds() - currentTime.getSeconds() >= 0)
		initialDelay = (time.getSeconds() - currentTime.getSeconds()) * 1000;
	else if (time.getSeconds() - currentTime.getSeconds() == 0)
		initialDelay = 0;
	else
		initialDelay = (60 + time.getSeconds() - currentTime.getSeconds()) * 1000;
	
	// 첫 번째 업데이트 예약
	setTimeout(function() {
		updateTimer();
		// 이후 60초마다 업데이트
		timerIDs[pc] = setInterval(updateTimer, 60000);
	}, initialDelay);
}

function getAllUser(ulist) {
	// 받아온 리스트를 HTML로 변환
	ulist.forEach(function(item) {
		use += 1;
		
		var id = item.id;
		var pc = item.use_seat;
		var time = item.use_start;
		
		var currentTime = new Date();
		
		// 특정 시간 파싱
		var specificTime = new Date(time);
		
		// 시간 차이 계산 (밀리초 단위)
		var timeDifference = currentTime - specificTime;
		
		// 밀리초를 초, 분, 시간, 일 단위로 변환
		var minutes = Math.floor((timeDifference / 1000 / 60) % 60);
		var hours = Math.floor((timeDifference / (1000 * 60 * 60)) % 24);
		var days = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
		
		if (minutes < 10) minutes = '0' + minutes;
		if (hours < 10) hours = '0' + hours;
		if (days < 10) days = '0' + days;
		
		var times = "";
		
		if (days >= 1)
			times = days +  " : " + hours + " : " + minutes;
		else
			times = hours + " : " + minutes;
		
		var start = "시작 시간 : " + formatStartTime(time.split("T")[1]);
		
		document.getElementById("spc" + pc).innerHTML = "<br>" + id + "<br>" + times + "<br>" + start;
		document.getElementById("dpc-" + pc).style.display = "block";
		timer(id, pc, time);
	});
}

// 01부터 16까지의 요소를 생성하여 추가하는 함수
function generatePCSeats() {
	var parentElement = document.getElementById("admin-d4");

	// 01부터 16까지 반복하면서 요소를 생성하여 부모 요소에 추가합니다.
	for (var i = 1; i <= 16; i++) {
		if (i < 10) i = '0' + i;
		var seatElement = createPCSeatElement(i.toString());
		parentElement.appendChild(seatElement);
	}
}

// div 요소를 생성하여 특정 id와 클래스를 추가하고 반환하는 함수
function createPCSeatElement(seatNumber) {
	// div 요소 생성
	var div = document.createElement("div");
	// id 추가
	div.id = "pc" + seatNumber;
	// 클래스 추가
	div.className = "pc-seat";
	// 클릭 이벤트 핸들러 추가
	div.setAttribute("onclick", "seat('" + seatNumber + "')");
	
	// 내부 요소 생성 및 추가
	div.innerHTML = '<div class="pc-num"><span class="do-hyeon-regular pcnum">' + seatNumber + '</span></div>' +
					'<div class="pc-d1" id="dpc-' + seatNumber + '"><span class="do-hyeon-regular pcuser" id="spc' + seatNumber + '"></span></div>';
	
	return div;
}