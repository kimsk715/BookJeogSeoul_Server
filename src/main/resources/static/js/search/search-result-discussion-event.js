let currentPage = 1; // 현재 페이지
let isFetching = false; // 중복 로딩 방지
const pageSize = 8; // 한 번에 불러올 토론글 개수
let hasMoreData = true; // 더 이상 불러올 데이터가 있는지 여부

// 페이지 최초 로딩 시 첫 토론글 리스트 보여주기
window.addEventListener("DOMContentLoaded", () => {
    loadMoreDiscussions();
});

// 스크롤 이벤트 (무한스크롤)
window.addEventListener("scroll", async () => {
    if (!hasMoreData) return; // 🔹 더 이상 불러올 데이터가 없으면 이벤트 처리 중단

    const scrollTop = window.scrollY;
    const scrollHeight = document.documentElement.scrollHeight;
    const clientHeight = document.documentElement.clientHeight;

    if (scrollTop + clientHeight >= scrollHeight - 30 && !isFetching) {
        console.log("✅ 스크롤 감지, 추가 로딩 시도");
        await loadMoreDiscussions();
    }
});

// 토론글 무한스크롤 로딩 함수
let totalDiscussionCount = null; // 전체 개수 저장

const loadMoreDiscussions = async () => {
    if (isFetching) return;
    isFetching = true;

    const keyword = new URLSearchParams(window.location.search).get("keyword");
    const offset = (currentPage - 1) * pageSize;

    try {
        const { totalCount, discussions } = await searchResultDiscussionService.getDiscussionList(keyword, offset, sortType);

        // discussions가 비어있거나 더 이상 로딩할 게 없으면 return
        if (discussions.length === 0 || (totalDiscussionCount !== null && offset >= totalDiscussionCount)) {
            isFetching = false;
            return;
        }

        if (totalDiscussionCount === null) {
            totalDiscussionCount = totalCount;
        }

        await searchResultDiscussionLayout.showDiscussionList(totalCount, discussions);
        if ((currentPage * pageSize) >= totalCount) {
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

// 정렬 텍스트 → sortType 값 매핑
const sortValueMap = {
    "최신순": "new",
    "댓글순": "comment",
    "제목순": "name"
};

let sortType = "new"; // 기본 정렬

// 모달창 관련 요소
let previousCheckedLabel = null;
const sortModal = document.querySelector(".modal-section");
const openModalButton = document.querySelector(".filter-wrapper");

// 모달 열기
openModalButton.addEventListener("click", () => {
    previousCheckedLabel = document.querySelector(".modal-radio.checked");
    sortModal.style.display = "flex";
});

// 정렬 옵션 클릭
const optionButtons = document.querySelectorAll(".modal-radio");
optionButtons.forEach((optionButton) => {
    optionButton.addEventListener("click", () => {
        optionButtons.forEach((btn) => btn.classList.remove("checked"));
        optionButton.classList.add("checked");
    });
});

// 취소 버튼 클릭
const cancelButton = document.querySelector(".button-cancel");
cancelButton.addEventListener("click", () => {
    optionButtons.forEach((label) => label.classList.remove("checked"));
    if (previousCheckedLabel) {
        previousCheckedLabel.classList.add("checked");
    }
    sortModal.style.display = "none";
});

// 확인 버튼 클릭 → 정렬 적용
const confirmButton = document.querySelector(".button-primary");
confirmButton.addEventListener("click", () => {
    const selectedLabel = document.querySelector(".modal-radio.checked");
    const selectedText = selectedLabel.innerText;

    openModalButton.querySelector(".icon-arrow-bottom span").innerText = selectedText;

    sortType = sortValueMap[selectedText] || "new";

    currentPage = 1;
    document.querySelector("ul.discussion").innerHTML = ""; // 기존 리스트 초기화
    loadMoreDiscussions(); // 새로운 정렬 기준으로 불러오기
    window.scrollTo({ top: 0, behavior: "smooth" });

    sortModal.style.display = "none";
});