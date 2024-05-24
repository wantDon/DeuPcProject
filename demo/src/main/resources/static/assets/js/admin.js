var currentElement = null;

function seat(num) {
	var element = document.getElementById("pc" + num);
	currentElement = element;
	
	if (element.classList.contains("active")) {
		element.classList.remove("active");
		element.style.backgroundColor = "#ddd"
	} else {
		element.classList.add("active");
		element.style.backgroundColor = "#FFF"
		
		// Update modal content
        document.getElementById("seatDetails").innerText = "Selected seat number: " + num;
		$('#seatModal').modal('show');
	}
}

$(document).ready(function() {
	// 페이지가 로드될 때 실행되는 함수
	window.onload = function() { 		
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

    stompClient.connect({}, function(frame){
        stompClient.subscribe('/topic/login', function(message) {
			if (message.body.includes("logout")) {
				var id = message.body.split("/")[1];
				var pc = message.body.split("/")[2];
				alert(id + " " + pc);
				document.getElementById("spc" + pc).innerHTML = "";
				document.getElementById("dpc-" + pc).style.display = "none";
			} else {
				var id = message.body.split("/")[0];
				var pc = message.body.split("/")[1];
				alert(id + " " + pc);
				document.getElementById("spc" + pc).innerHTML = "<br>" + id + "<br>" + pc;
				document.getElementById("dpc-" + pc).style.display = "block";
			}
        });
    });
});

function getAllUser(ulist) {
	// 받아온 리스트를 HTML로 변환
    ulist.forEach(function(item) {
		var id = item.id;
		var pc = item.use_seat;
		var time = item.use_start;
		console.log(id + " " + pc + " " + time);
		document.getElementById("spc" + pc).innerHTML = "<br>" + id + "<br>" + pc;
		document.getElementById("dpc-" + pc).style.display = "block";
    });
}

// 01부터 16까지의 요소를 생성하여 추가하는 함수
function generatePCSeats() {
    // 부모 요소를 가져옵니다. 이 예시에서는 "container"라는 id를 가진 요소를 부모 요소로 사용합니다.
    var parentElement = document.getElementById("admin-d4");

    // 01부터 16까지 반복하면서 요소를 생성하여 부모 요소에 추가합니다.
    for (var i = 1; i <= 16; i++) {
		if (i < 10) i = 0 + i;
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

window.onload = generatePCSeats;