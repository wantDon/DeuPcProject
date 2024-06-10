document.addEventListener('DOMContentLoaded', function() {
	const filterButtons = document.querySelectorAll('.filter-btn');
	const posts = document.querySelectorAll('.post-item');
	const cartItems = document.getElementById('cart-items');
	const totalPriceElem = document.getElementById('total-price');
	let totalPrice = 0;

	// Filter functionality
	filterButtons.forEach(button => {
		button.addEventListener('click', () => {
			const filter = button.getAttribute('data-filter');

			posts.forEach(post => {
				const category = post.getAttribute('data-category');

				if (filter === 'all' || category === filter) {
					post.style.display = 'block';
				} else {
					post.style.display = 'none';
				}
			});
		});
	});

	const addToCartButtons = document.querySelectorAll('.add-to-cart');
	addToCartButtons.forEach(button => {
		button.addEventListener('click', () => {
			const name = button.getAttribute('data-name');
			const price = parseInt(button.getAttribute('data-price'), 10);
			const num = parseInt(button.getAttribute('data-num'), 10);
			let cartItem = document.querySelector(`li[data-name="${name}"]`);
	
			if (cartItem) {
				const quantityElem = cartItem.querySelector('.quantity-input');
				quantityElem.value = parseInt(quantityElem.value, 10) + 1;
				updateCartItem(cartItem, price);
				return;
			} else {
				cartItem = document.createElement('li');
				cartItem.setAttribute('data-name', name);
				cartItem.setAttribute('data-num', num);
				cartItem.innerHTML = `
						${name} - <span class="price">${price}</span> 원
						<input type="number" class="quantity-input" value="1" min="1" style="width: 50px; margin-left: 10px;"/>
						<button class="remove-item btn btn-danger btn-sm">삭제</button>
					`;
				cartItems.appendChild(cartItem);
				cartItem.querySelector('.quantity-input').addEventListener('input', (event) => {
					updateCartItem(cartItem, price);
				});
				cartItem.querySelector('.remove-item').addEventListener('click', () => {
					const quantity = parseInt(cartItem.querySelector('.quantity-input').value, 10);
					const itemPrice = parseInt(cartItem.querySelector('.price').textContent, 10) / quantity;
					totalPrice -= itemPrice * quantity;
					totalPriceElem.textContent = totalPrice;
					cartItem.remove();
				});
			}
	
			totalPrice += price;
			totalPriceElem.textContent = totalPrice;
		});
	});

	const updateCartItem = (cartItem, unitPrice) => {
		const quantity = parseInt(cartItem.querySelector('.quantity-input').value, 10);
		const priceElem = cartItem.querySelector('.price');
		const newPrice = unitPrice * quantity;
		priceElem.textContent = `${newPrice} `;
		updateTotalPrice();
	};

	const updateTotalPrice = () => {
		const prices = document.querySelectorAll('.price');
		totalPrice = Array.from(prices).reduce((total, priceElem) => {
			return total + parseInt(priceElem.textContent, 10);
		}, 0);
		totalPriceElem.textContent = totalPrice;
	};

	document.getElementById('checkout-btn').addEventListener('click', requestPay);

	function requestPay() {
IMP.init("imp24061400");
IMP.request_pay({
pg: "html5_inicis",
pay_method: "card",
merchant_uid: createOrderNum(),
name: "테스트",
amount: 100,
buyer_email: "aaa@naver.com",
buyer_name: "홍길동",
}, function (rsp) {
if (rsp.success) {
// 결제 성공 시 실행되는 코드
const orders = [];
const cartItems = document.querySelectorAll('#cart-items li');

cartItems.forEach(item => {
const foodNum = item.getAttribute('data-foodNum=${food.food_num}');
const orderCount = parseInt(item.querySelector('.quantity-input').value, 10);
orders.push({ food_num: foodNum, order_count: orderCount });
});

var totalPrices = document.getElementById("total-price").textContent;
var userWish = $('#user-wish').val();


var data = {
total_price: totalPrices,
method: "card",
pay_req: userWish,
pay_div: 0,
pay_state: 0,
orders: orders
};

$.ajax({
url: '/payment/process',
type: 'POST',
contentType: 'application/json',
dataType: 'json',
data: JSON.stringify(data),
success: function (response) {
alert("주문이 완료되었습니다.");
// 장바구니 초기화
cartItems.forEach(item => item.remove());
totalPriceElem.textContent = '0';
$('#user-wish').val('');
},
error: function (jqXHR, textStatus, errorThrown) {
console.error('Error: ' + textStatus, errorThrown);
}
});
} else {
// 결제 실패 시 실행되는 코드
const orders = [];
const cartItems = document.querySelectorAll('#cart-items li');

cartItems.forEach(item => {
const foodNum = item.getAttribute('data-num');
const orderCount = parseInt(item.querySelector('.quantity-input').value, 10);
orders.push({ food_num: foodNum, order_count: orderCount });
});

var totalPrices = document.getElementById("total-price").textContent;
var userWish = $('#user-wish').val();


var data = {
total_price: totalPrices,
method: "card",
pay_req: userWish,
pay_div: 0,
pay_state: 0,
orders: orders
};

$.ajax({
url: '/payment/process',
type: 'POST',
contentType: 'application/json',
dataType: 'json',
data: JSON.stringify(data),
success: function (response) {
alert("주문이 완료되었습니다.");
// 장바구니 초기화
cartItems.forEach(item => item.remove());
totalPriceElem.textContent = '0';
totalPrice = 0;
$('#user-wish').val('');
},
error: function (jqXHR, textStatus, errorThrown) {
console.error('Error: ' + textStatus, errorThrown);
}
});
alert("결제 실패\n" + rsp.error_msg);
}
});
}


	//주문번호 만들기
	function createOrderNum() {
		const date = new Date();
		const year = date.getFullYear();
		const month = String(date.getMonth() + 1).padStart(2, "0");
		const day = String(date.getDate()).padStart(2, "0");
		let orderNum = year + month + day;
		
		for (let i = 0; i < 10; i++) {
			orderNum += Math.floor(Math.random() * 8);
		}
		return orderNum;
	}
});