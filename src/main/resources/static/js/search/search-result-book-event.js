let currentPage = 1; // 현재 페이지
let isFetching = false; // 중복 로딩 방지
const pageSize = 35; // 한 번에 불러올 도서 개수
let sortType = "Accuracy"; // 기본 정렬 기준
let hasMoreData = true; // 더 이상 불러올 데이터가 있는지 여부

// 페이지 최초 로딩 시 첫 도서 리스트 보여주기
window.addEventListener("DOMContentLoaded", () => {
    loadMoreBooks();
});

// 스크롤 이벤트 추가
window.addEventListener("scroll", async () => {
    if (!hasMoreData) return;

    const scrollTop = window.scrollY;
    const scrollHeight = document.documentElement.scrollHeight;
    const clientHeight = document.documentElement.clientHeight;

    if (scrollTop + clientHeight >= scrollHeight - 30 && !isFetching) {
        console.log("✅ 스크롤 감지, 추가 로딩 시도");
        await loadMoreBooks();
    }
});

// 도서 무한스크롤 로딩 함수
const loadMoreBooks = async () => {
    if (isFetching || !hasMoreData) return; // 이미 로딩 중이거나 데이터 없으면 리턴
    isFetching = true;

    const keyword = new URLSearchParams(window.location.search).get("keyword");
    const startIndex = (currentPage - 1) * pageSize + 1; // 알라딘 API는 1부터 시작

    try {
        const { totalResults, books } = await searchResultBookService.getBookList(keyword, startIndex, pageSize, sortType);
        searchResultBookLayout.showBookList(totalResults, books);

        if ((currentPage * pageSize) >= totalResults) {
            hasMoreData = false;
            console.log("더 이상 불러올 데이터 없음");
        } else {
            currentPage++;
        }
    } catch (error) {
        console.error("로딩 에러:", error);
    } finally {
        isFetching = false;
    }
};

// 정렬 텍스트를 알라딘 API의 Sort값으로 변환하기 위한 맵
const sortValueMap = {
    "정확도순": "Accuracy",
    "출간일순": "PublishTime",
    "제목순": "Title"
};

// 모달창 열기 전 체크된 라벨 저장
let previousCheckedLabel = null;

// 모달창 관련 요소들
const sortModal = document.querySelector(".modal-section");
const openModalButton = document.querySelector(".filter-wrapper");

// 모달창 열기
openModalButton.addEventListener("click", (e) => {
    previousCheckedLabel = document.querySelector(".modal-radio.checked");
    sortModal.style.display = "flex";
});

// 정렬 옵션 선택 시 스타일 적용
const optionButtons = document.querySelectorAll(".modal-radio");
const optionTexts = document.querySelectorAll(".label");

optionButtons.forEach((optionButton) => {
    optionButton.addEventListener("click", (e) => {
        optionButtons.forEach((btn) => btn.classList.remove("checked"));
        optionButton.classList.add("checked");
    });
});

// 정렬 모달창 닫기 (취소 시)
const cancelButton = document.querySelector(".button-cancel");

cancelButton.addEventListener("click", (e) => {
    optionButtons.forEach((label) => label.classList.remove("checked"));
    if (previousCheckedLabel) {
        previousCheckedLabel.classList.add("checked");
    }
    sortModal.style.display = "none";
});

// 정렬 모달창 "확인" 클릭 시
const confirmButton = document.querySelector(".button-primary");

confirmButton.addEventListener("click", (e) => {
    const selectedLabel = document.querySelector(".modal-radio.checked");
    const selectedText = selectedLabel.innerText;

    openModalButton.querySelector(".icon-arrow-bottom span").innerText = selectedText;
    sortType = sortValueMap[selectedText] || "Accuracy";

    // 리스트 초기화 + 다시 불러오기
    currentPage = 1;
    hasMoreData = true;
    document.querySelector("ul.book-list").innerHTML = "";
    loadMoreBooks();
    window.scrollTo({ top: 0, behavior: "smooth" });

    sortModal.style.display = "none";
});