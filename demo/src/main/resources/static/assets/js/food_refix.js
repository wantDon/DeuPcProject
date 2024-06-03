document.addEventListener("DOMContentLoaded", function() {
    // 드롭다운 메뉴 아이템 클릭 시 해당 카테고리의 상품만 필터링하여 출력
    var dropdownItems = document.querySelectorAll('.dropdown-item');
    console.log(dropdownItems);

    dropdownItems.forEach(function(item) {
        item.addEventListener('click', function() {
            var selectedCategory = item.textContent.trim();
            filterByCategory(selectedCategory);
        });
    });


});

function filterByCategory(selectedCategory) {
    var tableRows = document.querySelectorAll('.table tbody tr');

    tableRows.forEach(function(row) {
        var categoryCell = row.querySelector('td:nth-child(2)'); // 카테고리 열의 인덱스를 선택
        var category = categoryCell.textContent.trim();

        if (category === selectedCategory) {
            row.style.display = 'table-row'; // 선택된 카테고리와 일치하는 경우 행을 보이도록 설정
        } else {
            row.style.display = 'none'; // 일치하지 않는 경우 행을 숨김
        }
    });
}