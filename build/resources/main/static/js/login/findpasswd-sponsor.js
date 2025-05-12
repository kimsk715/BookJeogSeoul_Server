
// 나중에 꼭 다시 할거지만 일단 지금은 이렇게..
// 일반 회원 비밀번호 찾기 페이지 js 입니다.
const emailInput = document.querySelector("#bjs-email");

const emailclrBtn = document.querySelector("#clear-button-email");

const nextBtn = document.querySelector("#login-btn");
const checkSubmit = document.querySelector("#info-check")

const modal = document.querySelector("#modal");
const modalCloseBtn = document.querySelector(
    "#modal-wrap > .modal-footer > button"
);
const modalHeader = document.querySelector(".modal-header > strong");
const modalBody = document.querySelector(".modal-body > p");

const checkInput = () => {
    if (
        emailInput.value !== ""
    ) {
        nextBtn.style.opacity = "1";
        nextBtn.style.cursor = "pointer";
    } else {
        nextBtn.style.opacity = "0.5";
        nextBtn.style.cursor = "not-allowed";
    }
};

function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

emailInput.addEventListener("input", () => {
    if (emailInput.value !== "") {
        emailclrBtn.style.display = "block";
    } else {
        emailclrBtn.style.display = "none";
    }
    checkInput();
});






emailInput.addEventListener("focus", () => {
    if (emailInput.value !== "") {
        emailclrBtn.style.display = "block";
    }
});



emailInput.addEventListener("blur", () => {
    emailclrBtn.style.display = "none";
});


emailclrBtn.addEventListener("mousedown", (e) => {
    e.preventDefault();
    emailInput.value = "";
    emailclrBtn.style.display = "none";
    checkInput();
});




// 이메일 형식 다르면 모달창띄움
nextBtn.addEventListener('click', () =>{
    if(isValidEmail(emailInput.value)) {
        checkSubmit.submit();
    } else {
        modal.classList.add("fade-in");
        modalHeader.innerText = "이메일 형식이 다릅니다"
        setTimeout(() => {
            modal.style.display = "flex";
        }, 500);
    }
})

// 모달 테스트
// 모달 창을 띄우기 위한 코드
// 안쓸거면 주석 하면됨


modalCloseBtn.addEventListener("click", () => {
    modal.classList.add("fade-out");
    modal.classList.remove("fade-in");
    setTimeout(() => {
        modal.style.display = "none";
    }, 500);
});

window.addEventListener("DOMContentLoaded", () => {
    const params = new URLSearchParams(window.location.search);
    if (params.get("result") === "fail") {
        modal.classList.add("fade-in");
        setTimeout(() => {
            modal.style.display = "flex";
        }, 500);
    }
});
