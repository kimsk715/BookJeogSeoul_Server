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

// 게시글 숫자가 전체 카운트에 반영
function updatePostCount() {
    const postCount = document.querySelector("span.total-count");
    const count = document.querySelectorAll(".post-list > li").length;
    postCount.innerText = "전체 " + count;
}
updatePostCount();

// 정렬 버튼 누르면 모달창
const sortButton = document.querySelector(".sort-filter");
const sortModal = document.querySelector(".modal-overlay");

sortButton.addEventListener("click", (e) => {
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
        sortButton.querySelector("span").innerText = selectedText;
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

// 메뉴 누르면 삭제 메뉴 뜸
const moreButtons = document.querySelectorAll(".more-button");
const deleteOverlay = document.querySelector(".modal-overlay2");

let targetListItem = null; // 삭제할 게시물 추적용

moreButtons.forEach((moreButton) => {
    moreButton.addEventListener("click", (e) => {
        e.stopPropagation();

        // 현재 게시물 저장
        targetListItem = moreButton.closest(".list-item");

        deleteOverlay.style.display = "flex";
        deleteModal.style.display = "block";
    });
});

// 삭제하기 메뉴(삭제하기 누르면 확인창 뜸)
const cancelDelete = document.querySelector(".delete-cancel");
const progressDelete = document.querySelector(".delete-button");
const deleteModal = document.querySelector(".delete-menu");
const realDelModal = document.querySelector(".delete-confirm");

const confirmCancel = document.querySelector(".real-delete-cancel");
const confirmDelete = document.querySelector(".real-delete-confirm");

// 취소 버튼을 누르면
cancelDelete.addEventListener("click", (e) => {
    deleteOverlay.style.display = "none";
    deleteModal.style.display = "none";
    targetListItem = null;
});

// 삭제하기 버튼 누르면 다음 단계로
progressDelete.addEventListener("click", (e) => {
    deleteModal.style.display = "none";
    realDelModal.style.display = "block";
});

// 삭제 확인창에서 취소 누르면
confirmCancel.addEventListener("click", (e) => {
    deleteOverlay.style.display = "none";
    realDelModal.style.display = "none";
    targetListItem = null;
});

// 삭제 확인창에서 확인 누르면
confirmDelete.addEventListener("click", (e) => {
    if (targetListItem) {
        targetListItem.remove();
        showToast("선택한 독후감이 삭제되었습니다.");
    }

    deleteOverlay.style.display = "none";
    realDelModal.style.display = "none";
    targetListItem = null;
    updatePostCount();
});
