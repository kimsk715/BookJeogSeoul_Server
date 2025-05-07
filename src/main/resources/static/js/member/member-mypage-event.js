document.addEventListener("DOMContentLoaded", () => {
    mypageLayout.initProgressBars();

    const followBtn = document.querySelector(".add-book");

    if (followBtn) {
        const memberId = followBtn.getAttribute("data-id");

        // 초기 팔로우 상태 확인
        memberMypageService.checkFollowStatus(memberId)
            .then((isFollowing) => {
                updateFollowButton(followBtn, isFollowing);
            })
            .catch(() => {
                showToast("팔로우 상태를 확인할 수 없습니다.");
            });

        // 팔로우 버튼 클릭 시
        followBtn.addEventListener("click", async () => {
            const isActive = followBtn.classList.contains("active");

            try {
                if (isActive) {
                    await memberMypageService.deleteFollow(memberId);
                    updateFollowButton(followBtn, false);
                    showToast("팔로우를 해제했습니다.");
                } else {
                    await memberMypageService.addFollow(memberId);
                    updateFollowButton(followBtn, true);
                    showToast("팔로우했습니다.");
                }
            } catch (error) {
                console.error("팔로우/언팔로우 에러:", error);
                showToast("작업을 완료할 수 없습니다.");
            }
        });
    }
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

// 스크랩한 도서 누르면 상세페이지로 이동
document.addEventListener("click", (e) => {
    const target = e.target.closest(".book-picture-link, .book-metalink");
    if (!target) return;

    const isbn = target.dataset.isbn;
    if (isbn) {
        window.location.href = `/book/detail/${isbn}`;
    }
});

/**
 * 팔로우 버튼 상태 업데이트
 * @param {HTMLElement} button
 * @param {boolean} isFollowing
 */
function updateFollowButton(button, isFollowing) {
    button.classList.toggle("active", isFollowing);
    button.querySelector("span").innerText = isFollowing ? "언팔로우" : "팔로우";
}

/**
 * 토스트 메시지 출력
 * @param {string} message
 */
function showToast(message) {
    const toast = document.createElement("div");
    toast.className = "toast";
    toast.innerHTML = `<p>${message}</p>`;

    document.querySelector("#wrap").appendChild(toast);

    setTimeout(() => {
        toast.remove();
    }, 2500); // 2.5초 후 자동 제거
}
