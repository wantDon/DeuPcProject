$(document).ready(function() {
	getOrderCount();
	
	var socket = new SockJS('/ws'); // WebSocket 서버 URL
	var stompClient = Stomp.over(socket);
	
	stompClient.connect({}, function() {
		stompClient.subscribe('/topic/order', function(message) {
			if (message.body.includes("success")) {
				getOrderCount();
				showNotification("새로운 주문이 있습니다.");
			}
		});
	});
});

function getOrderCount() {
	$.ajax({
		url: '/pc/admin/order',
		type: 'POST',
		success: function(response) {
			if (response.error) {
				alert(response.error);
			} else {
				if (response && response.orderList) {
					const orderList = groupByPayNum(response.orderList);
					document.getElementById("orderNum").textContent = orderList.length;
				}
			}
		},
		error: function() {
			$('#result').empty();
			alert('서버 요청에 실패했습니다. 잠시 후 다시 시도 해주세요.');
		}
	});
}

function groupByPayNum(data) {
	const groupedData = data.reduce((acc, current) => {
		const { pay_num, pay_date, pay_req, food_name, order_count, id, pcnum } = current;
		if (!acc[pay_num]) {
			acc[pay_num] = {
				pay_num,
				pay_date,
				pay_req,
				id,
				pcnum,
				food_items: {}
			};
		}
		if (!acc[pay_num].food_items[food_name]) {
			acc[pay_num].food_items[food_name] = order_count;
		} else {
			acc[pay_num].food_items[food_name] += order_count;
		}
		return acc;
	}, {});
	return Object.values(groupedData).sort((a, b) => new Date(b.pay_date) - new Date(a.pay_date));
}

function changeButtonStyle(button, defaultColor, activeColor) {
    var allButtons = document.querySelectorAll('.btn-group-vertical .btn');
    allButtons.forEach(function(btn) {
        btn.classList.remove('active');
        if (btn.style.backgroundColor === activeColor) {
            btn.style.backgroundColor = defaultColor;
            btn.style.color = 'white';
        } else {
            btn.style.backgroundColor = btn.classList.contains('group1-btn') ? '#BD75C3' : 'gray';
            btn.style.color = 'white';
        }
    });
    button.classList.add('active');
    button.style.backgroundColor = activeColor;
    button.style.color = 'black';
}


function navigateToOrderPage() {
	window.location.href = '/pc/admin/order';
}

function navigateToNoticePage() {
    window.location.href = '/notice/'; // 공지 페이지로 이동
}

function navigateToItemPage() {
	window.location.href = '/inventory';
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