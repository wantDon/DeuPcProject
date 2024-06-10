$(document).ready(function() {
	// 페이지가 로드될 때 실행되는 함수
	window.onload = function() {
		getOrderList();
	};
	
	var socket = new SockJS('/ws'); // WebSocket 서버 URL
	var stompClient = Stomp.over(socket);
	
	stompClient.connect({}, function() {
		stompClient.subscribe('/topic/order', function(message) {
			if (message.body.includes("success")) {
				location.reload();
			}
		});
	});
});

function getOrderList() {
	$.ajax({
		url: '/pc/admin/order',
		type: 'POST',
		success: function(response) {
			if (response.error) {
				alert(response.error);
			} else {
				const orderList = response.orderList;
				const groupedData = groupByPayNum(orderList);
				renderData(groupedData);
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
	
function renderData(data) {
	const container = document.querySelector('.aorder-d3');
	container.innerHTML = '';
	
	data.forEach(item => {
		const foodList = Object.entries(item.food_items).map(([food_name, count]) => {
			return `${food_name}(${count}개)`;
		}).join(', ');
	
		const div = document.createElement('div');
		div.className = 'order-item';
		div.id = item.pay_num;
		div.innerHTML = `
			<div class="order-header">
				<div class="order-circle">
					<p><strong>PC ${item.pcnum}</strong></p>
				</div>
			</div>
			<div class="order-body">
				<p class="text-truncate"><strong>주문목록 : </strong>${foodList}</p>
				<p class="text-truncate"><strong>요청사항 : </strong>${item.pay_req}</p>
				<p>
					<strong>주문시각 : </strong>${item.pay_date}
					<button class="complete-btn" onclick="orderState(${item.pay_num}, event)">완료</button>
				</p>
			</div>
		`;
		div.addEventListener('click', getOrderDetail);
		container.appendChild(div);
	});
	
}

function orderState(num, event) {
    event.stopPropagation(); // 이벤트 전파를 막아 모달이 뜨지 않게 함
	$.ajax({
		url: '/pc/admin/state/' + num,
		type: 'POST',
		success: function(response) {
			if (response.error) {
				alert(response.error);
			} else {
				alert('주문 완료처리 되었습니다.');
				location.reload();
			}
		},
		error: function() {
			$('#result').empty();
			alert('서버 요청에 실패했습니다. 잠시 후 다시 시도 해주세요.');
		}
	});
}

function getOrderDetail() {
	const order = document.getElementById(this.id);
	if (order) {
		const orderListElement = order.querySelector('.text-truncate:nth-child(1)');
		const requestElement = order.querySelector('.text-truncate:nth-child(2)');
		
		const orderList = orderListElement ? orderListElement.textContent.trim() : '';
		const request = requestElement ? requestElement.textContent.trim() : '';
		
		// 모달에 내용 채우기
		document.getElementById('orderList').textContent = orderList.split(": ")[1];
		document.getElementById('request').textContent = request.split(": ")[1];
		
		// 모달 띄우기
		const myModal = new bootstrap.Modal(document.getElementById('orderModal'), {
			keyboard: false
		});
		myModal.show();
	}
}