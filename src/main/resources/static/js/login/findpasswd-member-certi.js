const checkBtn = document.querySelector("#check-btn");
const certiCode = document.querySelector("#bjs-certi-code")
const nextbtn = document.querySelector("#next-btn")

certiCode.addEventListener("input", () => {
    console.log("입력됨");

    if (certiCode.value.length === 5) {
        console.log("조건문입장");
        checkBtn.style.opacity = "1";
        checkBtn.style.cursor = "pointer";
    } else {
        checkBtn.style.opacity = "0.4";
        checkBtn.style.cursor = "not-allowed";
    }
});


checkBtn.addEventListener("click", (e) => {
    if (certiCode.value.length === 5) {
        modalHeader.innerText = "확인되었습니다!";
        modalBody.innerText = "다음 버튼을 눌러주세요.";
        modal.classList.remove("fade-out");
        modal.style.display = "flex";
        modal.classList.add("fade-in");
        nextbtn.style.opacity = "1";
        nextbtn.style.cursor = "pointer";
    }
//  여기서 검사하고 넘기면됨
        // modalHeader.innerText = "거의 다 되었어요!";
        // modalBody.innerText = "다음 버튼을 눌러주세요.";
        // modal.classList.remove("fade-out");
        // modal.style.display = "flex";
        // modal.classList.add("fade-in");
        // nextBtn.style.opacity = "1";
        // nextBtn.style.cursor = "pointer";

});





// 모달창
const modal = document.querySelector(".modal");
const modalCloseBtn = document.querySelector(
    ".modal-wrap > .modal-footer > button"
);
const modalHeader = document.querySelector(".modal-header > strong");
const modalBody = document.querySelector(".modal-body > div > p");

modalCloseBtn.addEventListener("click", () => {
    modal.classList.add("fade-out");
    modal.classList.remove("fade-in");
    setTimeout(() => {
        modal.style.display = "none";
    }, 500);
});