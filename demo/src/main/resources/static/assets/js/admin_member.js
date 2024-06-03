function handleFormSubmit(event) {
	// 검색 기준과 검색어 가져오기
	var searchCriteria = document.getElementById("searchCriteria").value;
	var searchKeyword = document.getElementById("searchKeyword").value;
	
	// 검색 기준이 '검색기준' 이거나 검색어가 없는 경우
	if (searchCriteria === "검색기준" || searchKeyword.trim() === "") {
		// 페이지 이동 막기
		event.preventDefault();
		// '/pc/admin/member' 페이지로 이동
		window.location.replace("/pc/admin/member");
	}
}

// 회원 탈퇴
function resign(id) {
	if (!confirm('탈퇴시키면 복구할 수 없습니다.\n정말로 회원 탈퇴를 진행하시겠습니까?')) {
        return false;
    }
	
	// AJAX 요청 생성
	var xhr = new XMLHttpRequest();
	xhr.open('POST', '/pc/admin/resign/' + id);
	
	// 요청 완료 시 실행될 함수
	xhr.onload = function() {
		if (xhr.status === 200) {
			alert("[" + id + "] 회원 탈퇴가 이루어졌습니다.");
			location.reload();
		} else {
			console.error('서버 요청 실패: ' + xhr.status);
		}
	};

	// 요청 보내기
	xhr.send();
}

function grade(sel) {
	var id = sel.id;
	var grade = sel.options[sel.selectedIndex].value;

	// AJAX 요청 생성
	var xhr = new XMLHttpRequest();
	xhr.open('POST', '/pc/admin/grade/' + id + "/" + grade);
	
	// 요청 완료 시 실행될 함수
	xhr.onload = function() {
		if (xhr.status === 200) {
			alert("[" + id + "] 회원 등급이 변경되었습니다.");
		} else {
			console.error('서버 요청 실패: ' + xhr.status);
		}
	};

	// 요청 보내기
	xhr.send();

}

// 페이지 로드 시에 실행
window.onload = function() {
	var currentUrl = window.location.href;
	
	if (currentUrl.includes("id")) {
		document.getElementById("searchCriteria").value = "id";
	} else if (currentUrl.includes("name")) {
		document.getElementById("searchCriteria").value = "name";
	} else if (currentUrl.includes("phone")) {
		document.getElementById("searchCriteria").value = "phone";
	} else if (currentUrl.includes("page=0")) {
		window.location.href = "/pc/admin/member";
	}
};