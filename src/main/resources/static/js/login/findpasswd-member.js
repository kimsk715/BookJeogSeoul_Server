// 나중에 꼭 다시 할거지만 일단 지금은 이렇게..
// 일반 회원 비밀번호 찾기 페이지 js 입니다.
const emailInput = document.querySelector("#bjs-email");
const nameInput = document.querySelector("#bjs-name");
const birthInput = document.querySelector("#bjs-birth");
const genderInput = document.querySelector("#bjs-gender");

const birthGenderClrBtn = document.querySelector("#clear-button-gender");
const nameclrBtn = document.querySelector("#clear-button-for-name");
const emailclrBtn = document.querySelector("#clear-button-email");

const checkInput = () => {
    if (
        genderInput.value !== "" &&
        birthInput.value !== "" &&
        nameInput.value !== "" &&
        emailInput.value !== ""
    ) {
        nextBtn.style.opacity = "1";
        nextBtn.style.cursor = "pointer";
    } else {
        nextBtn.style.opacity = "0.5";
        nextBtn.style.cursor = "not-allowed";
    }
};

emailInput.addEventListener("input", () => {
    if (emailInput.value !== "") {
        emailclrBtn.style.display = "block";
    } else {
        emailclrBtn.style.display = "none";
    }
    checkInput();
});

nameInput.addEventListener("input", () => {
    if (nameInput.value !== "") {
        nameclrBtn.style.display = "block";
    } else {
        nameclrBtn.style.display = "none";
    }
    checkInput();
});

birthInput.addEventListener("input", () => {
    if (birthInput.value.trim() !== "" || genderInput.value.trim() !== "") {
        console.log("값 있음");
        birthGenderClrBtn.style.display = "block";
    } else {
        birthGenderClrBtn.style.display = "none";
    }
    checkInput();
});

genderInput.addEventListener("input", () => {
    if (genderInput.value !== "" || birthInput.value !== "") {
        birthGenderClrBtn.style.display = "block";
    } else {
        birthGenderClrBtn.style.display = "none";
    }
    checkInput();
});

emailInput.addEventListener("focus", () => {
    if (emailInput.value !== "") {
        emailclrBtn.style.display = "block";
    }
});
nameInput.addEventListener("focus", () => {
    if (nameInput.value !== "") {
        nameclrBtn.style.display = "block";
    }
});
birthInput.addEventListener("focus", () => {
    if (birthInput.value !== "" && genderInput.value !== "") {
        birthGenderClrBtn.style.display = "block";
    }
});
genderInput.addEventListener("focus", () => {
    if (genderInput.value !== "" && birthInput.value !== "") {
        birthGenderClrBtn.style.display = "block";
    }
});

emailInput.addEventListener("blur", () => {
    emailclrBtn.style.display = "none";
});
nameInput.addEventListener("blur", () => {
    nameclrBtn.style.display = "none";
});
birthInput.addEventListener("blur", () => {
    birthGenderClrBtn.style.display = "none";
});

genderInput.addEventListener("blur", () => {
    birthGenderClrBtn.style.display = "none";
});

emailclrBtn.addEventListener("mousedown", (e) => {
    e.preventDefault();
    emailInput.value = "";
    emailclrBtn.style.display = "none";
    checkInput();
});

birthGenderClrBtn.addEventListener("mousedown", (e) => {
    e.preventDefault();
    birthInput.value = "";
    genderInput.value = "";
    birthGenderClrBtn.style.display = "none";
    checkInput();
});

nameclrBtn.addEventListener("mousedown", (e) => {
    e.preventDefault();
    nameInput.value = "";
    nameclrBtn.style.display = "none";
    checkInput();
});

const nextBtn = document.querySelector("#login-btn");
