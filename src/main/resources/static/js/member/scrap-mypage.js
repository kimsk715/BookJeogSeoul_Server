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

// 정렬 버튼 누르면 정렬 모달창
const modalButton = document.querySelector(".option-button");
const sortModal = document.querySelector(".modal-overlay");

modalButton.addEventListener("click", (e) => {
    sortModal.style.display = "flex";
});

// 정렬 버튼 누르면 스타일 변경
const radioLabels = document.querySelectorAll(".radio-label");

radioLabels.forEach((radioLabel) => {
    const radioInput = radioLabel.querySelector(".radio-input");
    radioInput.addEventListener("change", (e) => {
        radioLabels.forEach((radioLabel) =>
            radioLabel.classList.remove("active")
        );

        if (radioInput.checked) {
            radioLabel.classList.add("active");
        }
    });
});

// 적용 버튼 누르면 정렬 저장, 버튼 텍스트 변경
// 취소 버튼 누르면 초기화
const confirmButton = document.querySelector(".modal-confirm");
const cancelButton = document.querySelector(".modal-cancel");

// 적용 버튼: 선택된 라벨의 텍스트를 버튼에 적용
confirmButton.addEventListener("click", () => {
    const activeLabel = document.querySelector(".radio-label.active");
    if (activeLabel) {
        const selectedText = activeLabel.querySelector("span").innerText;
        modalButton.querySelector("span").innerText = selectedText;
        sortModal.style.display = "none";
    }
});

// 닫기 버튼: 선택 초기화
cancelButton.addEventListener("click", () => {
    radioLabels.forEach((label) => {
        const radio = label.querySelector(".radio-input");
        radio.checked = false;
        label.classList.remove("active");
        sortModal.style.display = "none";
    });
});

// 좋아요(스크랩) 버튼 누르면 스타일 변경
const scrapButtons = document.querySelectorAll(".book-badges-box");

scrapButtons.forEach((scrapButton) => {
    scrapButton.addEventListener("click", (e) => {
        if (scrapButton.style.display !== "none") {
            scrapButton.style.display = "none";
            showToast("스크랩을 해제했습니다.");
        }
    });
});
