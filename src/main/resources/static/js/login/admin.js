// 모달 테스트
// 모달 창을 띄우기 위한 코드
// 안쓸거면 주석 하면됨
const modal = document.querySelector("#modal");
const modalCloseBtn = document.querySelector(
    "#modal-wrap > .modal-footer > button"
);

modalCloseBtn.addEventListener("click", () => {
    modal.classList.add("fade-out");
    modal.classList.remove("fade-in");
    setTimeout(() => {
        modal.style.display = "none";
    }, 500);
});

loginBtn.addEventListener("click", () => {
    modal.classList.remove("fade-out");
    modal.style.display = "flex";
    modal.classList.add("fade-in");
});
