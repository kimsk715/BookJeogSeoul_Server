// 나중에 꼭 다시 할거지만 일단 지금은 이렇게..
// 일반 회원 비밀번호 찾기 페이지 js 입니다..
const emailInput = document.querySelector("#bjs-email");
const nameInput = document.querySelector("#bjs-name");
const birthInput = document.querySelector("#bjs-birth");
const genderInput = document.querySelector("#bjs-gender");
const pwInput = document.querySelector("#bjs-pw");
const phoneInput = document.querySelector("#bjs-phone");
const nickNameInput = document.querySelector("#bjs-nickname");

const birthGenderClrBtn = document.querySelector("#clear-button-gender");
const nameclrBtn = document.querySelector("#clear-button-name");
const emailclrBtn = document.querySelector("#clear-button-email");
const phoneclrBtn = document.querySelector("#clear-button-phone");
const pwclrBtn = document.querySelector("#clear-button-for-pw");
const nicknameclrBtn = document.querySelector("#clear-button-nickname");



const checkInput = () => {
    if (
        genderInput.value !== "" &&
        birthInput.value !== "" &&
        nameInput.value !== "" &&
        emailInput.value !== "" &&
        phoneInput.value !== "" &&
        pwInput.value !== "" &&
        nickNameInput.value !== ""
    ) {
        checkBtn.style.opacity = "1";
        checkBtn.style.cursor = "pointer";
    } else {
        checkBtn.style.opacity = "0.5";
        checkBtn.style.cursor = "not-allowed";
    }
};
// input

emailInput.addEventListener("input", () => {
    if (emailInput.value !== "") {
        emailclrBtn.style.display = "block";
    } else {
        emailclrBtn.style.display = "none";
    }
    checkInput();
});

nickNameInput.addEventListener("input", () => {
    if (nickNameInput.value !== "") {
        nicknameclrBtn.style.display = "block";
    } else {
        nicknameclrBtn.style.display = "none";
    }
    checkInput();
});

phoneInput.addEventListener("input", () => {
    if (phoneInput.value !== "") {
        phoneclrBtn.style.display = "block";
    } else {
        phoneclrBtn.style.display = "none";
    }
    checkInput();
});

pwInput.addEventListener("input", () => {
    if (pwInput.value !== "") {
        pwclrBtn.style.display = "block";
    } else {
        pwclrBtn.style.display = "none";
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

// focus


nickNameInput.addEventListener("focus", () => {
    if (nickNameInput.value !== "") {
        nicknameclrBtn.style.display = "block";
    }
});

phoneInput.addEventListener("focus", () => {
    if (phoneInput.value !== "") {
        phoneclrBtn.style.display = "block";
    }
});

pwInput.addEventListener("focus", () => {
    if (pwInput.value !== "") {
        pwclrBtn.style.display = "block";
    }
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


// blur

nickNameInput.addEventListener("blur", () => {
    if (nickNameInput.value !== "") {
        nicknameclrBtn.style.display = "block";
    } else {
        nicknameclrBtn.style.display = "none";
    }
});


phoneInput.addEventListener("blur", () => {
    if (phoneInput.value !== "") {
        phoneclrBtn.style.display = "block";
    } else {
        phoneclrBtn.style.display = "none";
    }
});

pwInput.addEventListener("blur", () => {
    if (pwInput.value !== "") {
        pwclrBtn.style.display = "block";
    } else {
        pwclrBtn.style.display = "none";
    }
});

emailInput.addEventListener("blur", () => {
    if (emailInput.value !== "") {
        emailclrBtn.style.display = "block";
    } else {
        emailclrBtn.style.display = "none";
    }
});
nameInput.addEventListener("blur", () => {
    if (nameInput.value !== "") {
        nameclrBtn.style.display = "block";
    } else {
        nameclrBtn.style.display = "none";
    }
});
birthInput.addEventListener("blur", () => {
    if (birthInput.value !== "" || genderInput.value !== "") {
        birthGenderClrBtn.style.display = "block";
    } else {
        birthGenderClrBtn.style.display = "none";
    }
});

genderInput.addEventListener("blur", () => {
    if (genderInput.value !== "" || birthInput.value !== "") {
        birthGenderClrBtn.style.display = "block";
    } else {
        birthGenderClrBtn.style.display = "none";
    }
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

pwclrBtn.addEventListener("mousedown", (e) => {
    e.preventDefault();
    pwInput.value = "";
    pwclrBtn.style.display = "none";
    checkInput();
});

phoneclrBtn.addEventListener("mousedown", (e) => {
    e.preventDefault();
    phoneInput.value = "";
    phoneclrBtn.style.display = "none";
    checkInput();
});

nicknameclrBtn.addEventListener("mousedown", (e) => {
    e.preventDefault();
    nickNameInput.value = "";
    nicknameclrBtn.style.display = "none";
    checkInput();
});


// 이메일, 생년월일 형식 체크
const checkBtn = document.querySelector("#check-btn");
const nextBtn = document.querySelector("#next-btn");
const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

const checkEmail = () => {
    emailcheckService.getTrueFalse(document.querySelector("#bjs-email").value)
}

const checkBirth = () => {
    if (birthInput.value.length > 8) {
        checkBtn.style.opacity = "0.5";
        checkBtn.style.cursor = "not-allowed";
    }
};



checkBtn.addEventListener("click", (e) => {
    if ((emailInput.value && !pattern.test(emailInput.value)) ||
        birthInput.value.length < 6 || checkEmail())
    {
        console.log(checkEmail());
        modalHeader.innerText = "형식&중복 오류";
        modalBody.innerText = "이메일 또는 생년월일 형식&중복을 확인해주세요.";
        modal.classList.remove("fade-out");
        modal.style.display = "flex";
        modal.classList.add("fade-in");
    } else if (
        (emailInput.value && pattern.test(emailInput.value)) ||
        birthInput.value.length >= 6 || checkEmail()
    ) {
        modalHeader.innerText = "확인 완료!!";
        modalBody.innerText = "다음 버튼을 눌러주세요 :)";
        modal.classList.remove("fade-out");
        modal.style.display = "flex";
        modal.classList.add("fade-in");
        nextBtn.style.opacity = "1";
        nextBtn.style.cursor = "pointer";
    } else {
        modal.classList.remove("fade-out");
        modal.style.display = "flex";
        modal.classList.add("fade-in");
    }
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



