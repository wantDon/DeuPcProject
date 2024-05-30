function handleFormSubmit(event) {
	// 검색 기준과 검색어 가져오기
	var searchCriteria = document.getElementById("searchCriteria").value;
	var searchKeyword = document.getElementById("searchKeyword").value;
	
	// 검색 기준이 '검색기준' 이거나 검색어가 없는 경우
	if (searchCriteria === "검색기준" || searchKeyword.trim() === "") {
		// 페이지 이동 막기
		event.preventDefault();
		// '/pc/admin/member' 페이지로 이동
		window.location.href = "/pc/admin/member";
	}
}

// 페이지 로드 시에 실행
window.onload = function() {
	// 현재 URL 가져오기
	var currentUrl = window.location.href;
	
	// 쿼리 문자열이 포함된 경우
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