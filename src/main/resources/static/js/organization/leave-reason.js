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

// 탈퇴사유에 기타를 체크하면 반드시 내용을 입력하게
const textarea = document.querySelector(".leave-text-area");
const otherRadio = document.querySelector("#check-6");
const submitBtn = document.querySelector("button.yellow");

submitBtn.addEventListener("click", (e) => {
    if (otherRadio.checked && textarea.value.trim() === "") {
        showToast("탈퇴사유를 입력해주세요");
        textarea.focus();
        e.preventDefault(); // 폼 전송 또는 페이지 이동 방지
    }
});
