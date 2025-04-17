// 카테고리 버튼 이벤트
const categoryBtn = document.querySelectorAll(".bjs-tab-item");
console.log(categoryBtn);

categoryBtn.forEach((btn) => {
    btn.addEventListener("click", (e) => {
        // 모든 탭에서 active 제거
        categoryBtn.forEach((btn) => btn.classList.remove("active"));
        console.log("click");
        btn.classList.add("active");
    });
});

// 햄버거 버튼 이벤트
const hamburgerBtn = document.querySelector(".hamburger");
const hamburgerMenu = document.querySelector(".hamburger-popup-wrap");

hamburgerBtn.addEventListener("click", () => {
    if (hamburgerBtn.classList.contains("active")) {
        hamburgerBtn.classList.remove("active");
        hamburgerMenu.style.display = "none";
    } else {
        hamburgerBtn.classList.add("active");
        hamburgerMenu.style.display = "";
    }
});

// ul 태그안에 요소 없으면 null text 출력 하는 이벤트
const ulTag = document.querySelector(".favorites-list");
const nullText = document.querySelector(".null-text");

if (ulTag.children.length === 0) {
    nullText.style.display = "block";
} else {
    nullText.style.display = "none";
}

// 카테고리 리셋
const resetBtn = document.querySelector(".reset-btn");

resetBtn.addEventListener("click", () => {
    ulTag.innerHTML = "";
    nullText.style.display = "block";
});

const inputBtn = document.querySelectorAll(".input-btn");

inputBtn.forEach((btn) => {});
