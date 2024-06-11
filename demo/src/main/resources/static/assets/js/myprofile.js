function showPasswordModal() {
	document.getElementById("passwordModal").style.display = "block";
}

function hidePasswordModal() {
	document.getElementById("passwordModal").style.display = "none";
}

// 변경된 JavaScript 코드에서 사용자 ID를 직접 사용
function changePassword() {
	var pwd = document.getElementById("pwd").value;
	var pwd1 = document.getElementById("pwd1").value;
	var id = document.getElementById("id").textContent.trim();

	if (pwd !== pwd1) {
		alert("비밀번호가 일치하지 않습니다.");
		return;
	}
	
	var jsonData = {
		newPassword: pwd,
		userId: id
	};

	$.ajax({
		url: "/myprofile/update-password",
		type: "post",
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify(jsonData),
		success: function(data) {
			if(data.success) {
				alert("비밀번호가 변경되었습니다.");
				location.reload();
			} else {
				alert("오류입니다. 다시 시도해주세요.");
			}
		},
		error: function(xhr){
			console.log(xhr);
		}
	});
}

document.addEventListener('DOMContentLoaded', function() {
	var table = document.getElementById('orderHistory');
	var rows = table.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
	var rowsPerPage = 20;
	var currentPage = 1;
	var totalPages = Math.ceil(rows.length / rowsPerPage);

	function showPage(page) {
		for (var i = 0; i < rows.length; i++) {
			rows[i].style.display = (i >= (page - 1) * rowsPerPage && i < page * rowsPerPage) ? '' : 'none';
		}
	}

	function setupPagination() {
		var pagination = document.querySelector('.pagination');
		pagination.innerHTML = '';
	
		var prev = document.createElement('li');
		prev.className = 'page-item';
		prev.innerHTML = '<a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>';
		pagination.appendChild(prev);
	
		for (var i = 1; i <= totalPages; i++) {
			var pageItem = document.createElement('li');
			pageItem.className = 'page-item' + (i === currentPage ? ' active' : '');
			pageItem.innerHTML = '<a class="page-link" href="#">' + i + '</a>';
			pageItem.addEventListener('click', (function(page) {
				return function() {
					currentPage = page;
					showPage(page);
					setupPagination();
				}
			})(i));
			pagination.appendChild(pageItem);
		}

		var next = document.createElement('li');
		next.className = 'page-item';
		next.innerHTML = '<a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>';
		pagination.appendChild(next);
		
		prev.addEventListener('click', function() {
			if (currentPage > 1) {
				currentPage--;
				showPage(currentPage);
				setupPagination();
			}
		});

		next.addEventListener('click', function() {
			if (currentPage < totalPages) {
				currentPage++;
				showPage(currentPage);
				setupPagination();
			}
		});
	}

	showPage(currentPage);
	setupPagination();
});