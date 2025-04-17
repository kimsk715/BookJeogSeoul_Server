// 검색어를 입력하면 뜨는 내용삭제 x 버튼을 누르면 입력값이 날아감
const searchInput = document.querySelector("#input-search");
const removeTextButton = document.querySelector(".clear-button");

searchInput.addEventListener("input", (e) => {
    // 값이 있을 때
    if (searchInput.value.trim() !== "") {
        removeTextButton.style.display = "flex";
    } else {
        removeTextButton.style.display = "none";
    }
});

removeTextButton.addEventListener("click", (e) => {
    searchInput.value = "";
    removeTextButton.style.display = "none";
});

// 최근 검색어가 없으면 내용이 없다는 p태그 출현
const recentHeader = document.querySelector(".recent-header");
const recentWord = document.querySelector(".recent-word");
const recentNull = document.querySelector(".recent-null");

// 전체삭제 버튼을 누르면 최근 검색어가 다 사라짐
const results = document.querySelectorAll(".search-recent-record");
const removeAllButton = document.querySelector(".search-deleteall");

removeAllButton.addEventListener("click", (e) => {
    results.forEach((result) => {
        result.remove();
        checkRecentListEmpty();
    });
});

// 최근 검색어 옆의 x 버튼을 누르면 하나만 사라짐
const removeOneButtons = document.querySelectorAll(".icon-recent-x");

removeOneButtons.forEach((removeOneButton) => {
    removeOneButton.addEventListener("click", (e) => {
        removeOneButton.parentElement.remove();
        checkRecentListEmpty();
    });
});

// 최근 검색어가 없는지 검사하는 기능
function checkRecentListEmpty() {
    if (recentWord.childElementCount === 0) {
        recentHeader.style.display = "none";
        recentWord.style.display = "none";
        recentNull.style.display = "block";
    }
}
