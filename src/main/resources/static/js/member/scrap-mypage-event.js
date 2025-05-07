// 토스트 기능(잠깐 나타났다가 사라지는 창)
function showToast(message) {
    const toast = document.createElement("div");
    toast.className = "toast";
    toast.innerHTML = `<p>${message}</p>`;
    document.querySelector("#wrap").appendChild(toast);

    setTimeout(() => {
        toast.remove();
    }, 2500);
}

// 정렬 모달창 관련
const modalButton = document.querySelector(".option-button");
const sortModal = document.querySelector(".modal-overlay");
const confirmButton = document.querySelector(".modal-confirm");
const cancelButton = document.querySelector(".modal-cancel");
const radioLabels = document.querySelectorAll(".radio-label");

let currentSort = "created-at";


// 정렬 모달창 열기
modalButton.addEventListener("click", () => {
    sortModal.style.display = "flex";
});

// 정렬 모달창 스타일 업데이트
radioLabels.forEach((radioLabel) => {
    const radioInput = radioLabel.querySelector(".radio-input");

    radioInput.addEventListener("change", () => {
        radioLabels.forEach((label) => label.classList.remove("active"));
        if (radioInput.checked) {
            radioLabel.classList.add("active");
        }
    });
});

// 정렬 적용 버튼 클릭
confirmButton.addEventListener("click", () => {
    const activeLabel = document.querySelector(".radio-label.active");
    if (activeLabel) {
        const selectedText = activeLabel.querySelector("span").innerText;
        modalButton.querySelector("span").innerText = selectedText;
        currentSort = activeLabel.querySelector(".radio-input").value;

        // 정렬 적용 후 도서 목록 새로 불러오기
        loadBooks(0, currentSort, true);

        sortModal.style.display = "none";
    }
});

// 정렬 취소 버튼 클릭
cancelButton.addEventListener("click", () => {
    radioLabels.forEach((label) => {
        label.classList.remove("active");
        label.querySelector(".radio-input").checked = false;
    });
    sortModal.style.display = "none";
});

// 책 클릭 시 상세 페이지로 이동
document.querySelector(".book-list").addEventListener("click", async (event) => {
    const scrapDeleteBtn = event.target.closest(".book-badges-box");
    const bookLink = event.target.closest(".book-picture-link, .book-metalink");

    if (scrapDeleteBtn) {
        const isbn = scrapDeleteBtn.dataset.isbn;
        await scrapMypageService.deleteBookScrap(isbn);
        event.stopPropagation(); // 링크 이동 방지
    } else if (bookLink) {
        const isbn = bookLink.dataset.isbn;
        if (isbn) {
            window.location.href = `/book/detail/${isbn}`;
        }
    }
});

// 하트 버튼 클릭 시 스크랩 해제
document.querySelector(".book-list").addEventListener("click", async (event) => {
    const scrapButton = event.target.closest(".book-badges-box");
    if (scrapButton) {
        const bookItem = scrapButton.closest(".book-item");
        const isbn = scrapButton.dataset.isbn;

        try {
            const isDeleted = await scrapMypageService.deleteBookScrap(isbn);
            if (isDeleted) {
                bookItem.remove();
                showToast("스크랩을 해제했습니다.");
            } else {
                showToast("스크랩 해제에 실패했습니다.");
            }
        } catch (error) {
            console.error("스크랩 해제 에러:", error);
            showToast("오류가 발생했습니다.");
        }
    }
});

/**
 * 도서 목록 불러오기
 * @param {number} offset - 시작 인덱스
 * @param {string} sort - 정렬 기준
 * @param {boolean} clearList - 목록 초기화 여부
 */
async function loadBooks(offset = 0, sort = "created-at", clearList = false) {
    if (clearList) {
        scrapMypageLayout.clearBookList();
    }

    try {
        const data = await scrapMypageService.getSortedBooks(offset, sort);

        if (data.books.length) {
            data.books.forEach(scrapMypageLayout.renderBookItem);
        } else if (clearList) {
            scrapMypageLayout.renderEmptyMessage();
        }
    } catch (error) {
        console.error("도서 목록 불러오기 에러:", error);
    }
}

// 페이지 로드 시 초기 데이터 로드
document.addEventListener("DOMContentLoaded", () => {
    loadBooks();
});
