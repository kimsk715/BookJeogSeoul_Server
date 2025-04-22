let currentPage = 1; // 현재 페이지
let isFetching = false; // 중복 로딩 방지
const pageSize = 35; // 한 번에 불러올 도서 개수

// 페이지 최초 로딩 시 첫 도서 리스트 보여주기
window.addEventListener("DOMContentLoaded", () => {
    loadMoreBooks();
});

// 스크롤 이벤트 추가
window.addEventListener("scroll", async () => {
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
    if (isFetching) return; // 이미 로딩 중이면 리턴
    isFetching = true;

    const keyword = new URLSearchParams(window.location.search).get("keyword"); // 검색 키워드 추출
    const startIndex = (currentPage - 1) * pageSize + 1; // 알라딘 API는 1부터 시작

    try {
        // 서버에서 도서 데이터 fetch
        const { totalResults, books } = await searchResultBookService.getBookList(keyword, startIndex, pageSize, sortType);

        // 가져온 책 목록을 화면에 렌더링
        searchResultBookLayout.showBookList(totalResults, books);
        currentPage++; // 페이지 증가
    } catch (error) {
        console.error("로딩 에러:", error);
    } finally {
        isFetching = false; // 로딩 완료
    }
};

// 정렬 텍스트를 알라딘 API의 Sort값으로 변환하기 위한 맵
const sortValueMap = {
    "정확도순": "Accuracy",
    "출간일순": "PublishTime",
    "제목순": "Title"
};

let sortType = "Accuracy"; // 기본 정렬 기준

// 모달창 열기 전 체크된 라벨 저장
let previousCheckedLabel = null;

// 모달창 관련 요소들
const sortModal = document.querySelector(".modal-section");
const openModalButton = document.querySelector(".filter-wrapper");

// 모달창 열기
openModalButton.addEventListener("click", (e) => {
    previousCheckedLabel = document.querySelector(".modal-radio.checked"); // 기존 선택 항목 저장
    sortModal.style.display = "flex"; // 모달 표시
});

// 정렬 옵션 선택 시 스타일 적용
const optionButtons = document.querySelectorAll(".modal-radio");
const optionTexts = document.querySelectorAll(".label");

optionButtons.forEach((optionButton) => {
    optionButton.addEventListener("click", (e) => {
        optionButtons.forEach((btn) => btn.classList.remove("checked")); // 이전 선택 제거
        optionButton.classList.add("checked"); // 현재 선택 적용
    });
});

// 정렬 모달창 닫기 (취소 시)
const cancelButton = document.querySelector(".button-cancel");

cancelButton.addEventListener("click", (e) => {
    optionButtons.forEach((label) => label.classList.remove("checked")); // 전체 해제

    if (previousCheckedLabel) {
        previousCheckedLabel.classList.add("checked"); // 이전 선택 복원
    }

    sortModal.style.display = "none"; // 모달 닫기
});

// 정렬 모달창 "확인" 클릭 시
const confirmButton = document.querySelector(".button-primary");

confirmButton.addEventListener("click", (e) => {
    const selectedLabel = document.querySelector(".modal-radio.checked"); // 현재 선택된 항목
    const selectedText = selectedLabel.innerText; // 텍스트 ("출간일순" 등)

    // 모달 열기 버튼 텍스트도 변경
    openModalButton.querySelector(".icon-arrow-bottom span").innerText = selectedText;

    // 실제 정렬 파라미터로 설정 (알라딘 API용)
    sortType = sortValueMap[selectedText] || "Accuracy";

    // 리스트 초기화 + 다시 불러오기
    currentPage = 1;
    document.querySelector("ul.book-list").innerHTML = ""; // 기존 리스트 제거
    loadMoreBooks(); // 새 정렬 기준으로 데이터 로딩
    window.scrollTo({ top: 0, behavior: "smooth" }); // 상단으로 부드럽게 이동

    sortModal.style.display = "none"; // 모달 닫기
});
