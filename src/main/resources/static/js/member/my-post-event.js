// 초기 상태
let currentSort = "recent";
let offset = 0;
const limit = 5;
let loading = false;
let totalCount = 0; // 전체 게시글 개수

// 페이지 로드 시 첫 데이터 요청
document.addEventListener("DOMContentLoaded", async () => {
    await loadPosts(true);
});

// 게시글 로드 함수
async function loadPosts(reset = false) {
    if (loading) return;
    loading = true;

    if (reset) {
        offset = 0;
        document.querySelector(".post-list").innerHTML = "";
    }

    try {
        const posts = await myPostService.fetchBookPosts(currentSort, offset);
        if (posts.length) {
            myPostLayout.renderPosts(posts);
            offset += posts.length;
        } else if (reset) {
            showToast("게시물이 없습니다.");
        } else {
            showToast("더 이상 게시물이 없습니다.");
        }
    } catch (error) {
        console.error("Error loading posts:", error);
        showToast("데이터 로딩 중 오류가 발생했습니다.");
    } finally {
        loading = false;
    }
}

// 토스트 기능
function showToast(message) {
    const toast = document.createElement("div");
    toast.className = "toast";
    toast.innerHTML = `<p>${message}</p>`;
    document.querySelector("#wrap").appendChild(toast);

    setTimeout(() => {
        toast.remove();
    }, 2500);
}

// 무한 스크롤
window.addEventListener("scroll", async () => {
    if (loading) return;
    if (window.innerHeight + window.scrollY >= document.body.offsetHeight - 100) {
        await loadPosts();
    }
});

// 정렬 모달 열기
const sortButton = document.querySelector(".sort-filter");
const sortModal = document.querySelector(".modal-overlay");

sortButton.addEventListener("click", () => {
    sortModal.style.display = "flex";
});

// 정렬 변경
const confirmButton = document.querySelector(".modal-confirm");
const cancelButton = document.querySelector(".modal-cancel");
const radioLabels = document.querySelectorAll(".radio-label");

confirmButton.addEventListener("click", async () => {
    const activeLabel = document.querySelector(".radio-label.active");
    if (activeLabel) {
        const selectedSort = activeLabel.querySelector("input").value;
        sortButton.querySelector("span").innerText = activeLabel.querySelector("span").innerText;

        sortModal.style.display = "none";

        // 정렬 기준 변경 시 데이터 리셋 및 재조회
        await myPostService.updateSort(selectedSort);
    }
});

cancelButton.addEventListener("click", () => {
    sortModal.style.display = "none";
    radioLabels.forEach(label => {
        const radio = label.querySelector(".radio-input");
        radio.checked = false;
        label.classList.remove("active");
    });
});

// 정렬 라벨 클릭 시 active 클래스 토글
document.addEventListener("click", (e) => {
    if (e.target.classList.contains("radio-input")) {
        radioLabels.forEach(label => label.classList.remove("active"));
        e.target.closest(".radio-label").classList.add("active");
    }
});

// 삭제 관련 이벤트
const deleteOverlay = document.querySelector(".modal-overlay2");
const deleteModal = document.querySelector(".delete-menu");
const realDelModal = document.querySelector(".delete-confirm");
let targetListItem = null;

// 삭제 메뉴 열기
document.addEventListener("click", (e) => {
    const target = e.target;

    if (target.classList.contains("more-button")) {
        targetListItem = target.closest(".list-item");
        deleteOverlay.style.display = "flex";
        deleteModal.style.display = "block";
    }
});

// 삭제 취소
document.addEventListener("click", (e) => {
    const target = e.target;

    if (target.classList.contains("delete-cancel") || target.classList.contains("real-delete-cancel")) {
        deleteOverlay.style.display = "none";
        deleteModal.style.display = "none";
        realDelModal.style.display = "none";
        targetListItem = null;
    }
});

// 삭제 확인창 열기
document.addEventListener("click", (e) => {
    const target = e.target;

    if (target.classList.contains("delete-button")) {
        deleteModal.style.display = "none";
        realDelModal.style.display = "block";
    }
});

// 삭제 확인
document.addEventListener("click", async (e) => {
    const target = e.target;

    if (target.classList.contains("real-delete-confirm") && targetListItem) {
        const postId = targetListItem.getAttribute("data-id");

        try {
            const response = await myPostService.deleteBookPost(postId);
            if (response.ok) {
                targetListItem.remove();
                showToast("독후감이 삭제되었습니다.");
                await loadPostCount(); // 삭제 후 전체 개수 업데이트
            } else {
                showToast("삭제에 실패했습니다.");
            }
        } catch (error) {
            console.error("Error deleting post:", error);
            showToast("삭제 중 오류가 발생했습니다.");
        }

        deleteOverlay.style.display = "none";
        realDelModal.style.display = "none";
        targetListItem = null;
    }
});
