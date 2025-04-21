// 정렬 버튼을 누르면 정렬 모달창 켜짐

// 모달창 열기 전에 현재 정렬을 저장하는 변수
let previousCheckedLabel = null;

const sortModal = document.querySelector(".modal-section");
const openModalButton = document.querySelector(".filter-wrapper");

openModalButton.addEventListener("click", (e) => {
    // 현재 저장된 라벨 저장
    previousCheckedLabel = document.querySelector(".modal-radio.checked");

    // 모달창 열기
    sortModal.style.display = "flex";
});

// 정렬 모달창에서 누르면 각 옵션이 선택되며 스타일이 달라짐
const optionButtons = document.querySelectorAll(".modal-radio");
const optionTexts = document.querySelectorAll(".label");

optionButtons.forEach((optionButton) => {
    optionButton.addEventListener("click", (e) => {
        // 모든 버튼에서 checked 클래스 제거
        optionButtons.forEach((btn) => btn.classList.remove("checked"));
        // 선택한 버튼만 checked 클래스 추가
        optionButton.classList.add("checked");
    });
});

// 취소 버튼 누르면 정렬 모달창 꺼짐
const cancelButton = document.querySelector(".button-cancel");

cancelButton.addEventListener("click", (e) => {
    // 현재 선택한 label 취소
    optionButtons.forEach((label) => {
        label.classList.remove("checked");
    });

    // 이전 라벨 다시 체크
    if (previousCheckedLabel) {
        previousCheckedLabel.classList.add("checked");
    }

    // 모달창 끄기
    sortModal.style.display = "none";
});

// 정렬 모달창에서 확인 누르면 버튼 텍스트 변경(인기순, 최신순)
const confirmButton = document.querySelector(".button-primary");

confirmButton.addEventListener("click", (e) => {
    // 현재 선택된 label 찾기
    const selectedLabel = document.querySelector(".modal-radio.checked");

    // 모달 열기 버튼 텍스트 변경
    openModalButton.querySelector(".icon-arrow-bottom span").innerText =
        selectedLabel.innerText;

    // 모달창 끄기
    sortModal.style.display = "none";
});
