function movePage(value) {
	if (value === "관리자") {
		location.href = "/pc/admin";
	} else if (value === "회원") {
		location.href = "/pc/member";
	} else if (value === "비회원") {
		location.href = "/pc/non-member";
	} else if (value === "메인") {
		location.href = "/pc/smain";
	}else if (value === "회원 충전") {
     	location.href = "/timePayment/paymentMain_User";
    }else if (value === "비회원 충전") {
		location.href = "/timePayment/paymentMain_nonUser";
	} else if (value === "회원로그인") {
		location.href = "/pc/login";
	} else if (value === "비회원로그인") {
		location.href = "/pc/nlogin";
	} else {
		location.href = "/pc/smain";
	}
}