// 토스트 기능(잠깐 나타났다가 사라지는 창)
function showToast(message) {
    const toast = document.createElement("div");
    toast.className = "toast";
    toast.innerHTML = `<p>${message}</p>`;

    document.querySelector("#wrap").appendChild(toast);

    setTimeout(() => {
        toast.remove();
    }, 2500); // 2.5초 후 자동 제거
}

// 비밀번호 입력하면 x버튼 출현
const passwordInput = document.querySelector("#input-215");
const clearButton = document.querySelector(".clear-button");
const passwordWrap = document.querySelector(".ipt-blk .input-text");

passwordInput.addEventListener("input", (e) => {
    if (e.target.value.trim() !== "") {
        passwordWrap.classList.add("flex");
    } else {
        passwordWrap.classList.remove("flex");
    }
});

clearButton.addEventListener("click", (e) => {
    passwordInput.value = "";
    passwordWrap.classList.remove("flex");
});

// input에 focus하면 테두리 스타일 변경
const passwordLabel = document.querySelector(".ipt-blk label");

passwordInput.addEventListener("focus", (e) => {
    passwordLabel.style.borderColor = "var(--ui-08)";
});

passwordInput.addEventListener("blur", (e) => {
    passwordLabel.style.borderColor = "var(--ui-06)";
});

// 입력 안 하고 확인 버튼을 누르면 메시지 출력, 비밀번호가 다르면 오류 출력
const confirmButton = document.querySelector(".btn-submit");

confirmButton.addEventListener("click", (e) => {
    if (passwordInput.value.trim() === "") {
        showToast("필수 요청 정보를 확인해 주세요.");
        e.preventDefault();
    } else if (e.target.value.length < 8 || e.target.value.length > 20) {
        showToast("비밀번호는 8~20자로 입력해주세요.");
        e.preventDefault();
    }
    // else if (passwordInput.value.trim() !== "맞는 비번") {
    //     showToast("비밀번호가 일치하지 않습니다.");
    // }
});
