document.addEventListener("DOMContentLoaded", () => {
    mypageLayout.initProgressBars();
});

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

// 스크랩한 도서 누르면 상세페이지로 이동
document.addEventListener("click", (e) => {
    const target = e.target.closest(".book-picture-link, .book-metalink");
    if (!target) return;

    const isbn = target.dataset.isbn;
    if (isbn) {
        window.location.href = `/book/detail/${isbn}`;
    }
});

