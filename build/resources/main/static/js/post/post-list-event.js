document.addEventListener("DOMContentLoaded", async () => {
    // 상단 탭 버튼 요소 가져오기
    const allPostButton = document.querySelector(".gtm-post-tab-recommend");
    const followPostButton = document.querySelector(".gtn-post-tab-following");

    // 무한스크롤, 페이징 관련 상태
    let offset = 0;
    let isFetching = false;
    let hasMore = true;

    // 페이지 로딩 시 최초 전체 목록 불러오기
    await fetchAndRenderPosts(true);

    // [탭] 전체 게시물 버튼 클릭 시
    allPostButton.addEventListener("click", async (e) => {
        e.preventDefault();
        console.log("전체버튼 눌림");
        postListLayout.setMode("all"); // 모드 변경
        offset = 0; // 오프셋 초기화
        hasMore = true; // 더 가져올 수 있게 리셋
        await fetchAndRenderPosts(true); // 다시 불러오기
        followPostButton.parentElement.classList.remove("active");
        allPostButton.parentElement.classList.add("active");
    });

    // [탭] 팔로우 게시물 버튼 클릭 시
    followPostButton.addEventListener("click", async (e) => {
        e.preventDefault();
        console.log("팔로잉 버튼 눌림");
        postListLayout.setMode("following"); // 모드 변경
        offset = 0;
        hasMore = true;
        await fetchAndRenderPosts(true);
        followPostButton.parentElement.classList.add("active");
        allPostButton.parentElement.classList.remove("active");
    });

    // [스크롤] 무한 스크롤
    window.addEventListener("scroll", async () => {
        if (!hasMore || isFetching) return; // 추가 로딩 불가 상태

        const scrollBottom = window.innerHeight + window.scrollY;
        const documentHeight = document.body.offsetHeight;

        // 스크롤이 거의 바닥에 닿으면
        if (scrollBottom >= documentHeight - 300) {
            await fetchAndRenderPosts();
        }
    });

    // 📦 게시물 데이터 가져오고 화면에 뿌리는 함수
    async function fetchAndRenderPosts(reset = false) {
        isFetching = true;
        const mode = postListLayout.getMode();
        console.log("현재 모드:", mode);

        let posts = [];
        try {
            // 모드에 따라 전체 or 팔로우 게시물 가져오기
            posts = mode === "all"
                ? await postListService.getAllPostList(offset)
                : await postListService.getFollowPostList(offset);

            console.log("받아온 데이터:", posts);

            // 가져온 게시물이 12개 미만이면 더 이상 가져올게 없음
            if (posts.length < 12) {
                hasMore = false;
            }

            await postListLayout.showPostList(posts, reset); // 카드 렌더링
            setupEventHandlers(); // 새로 추가된 카드에 이벤트 다시 연결
        } catch (error) {
            console.error("데이터 가져오기 실패:", error);
        } finally {
            offset += 12; // 다음 호출을 위해 오프셋 증가
            isFetching = false;
        }
    }

    // ✨ 새로 생성된 카드들의 버튼 이벤트 연결 함수
    function setupEventHandlers() {
        // [좋아요] 버튼 클릭 이벤트
        document.querySelectorAll(".card-item .btn.like").forEach((likeBtn) => {
            likeBtn.onclick = async (e) => {
                e.preventDefault();
                e.stopPropagation();

                const cardItem = likeBtn.closest(".card-item");
                const bookPostId = cardItem.dataset.bookPostId;
                if (!bookPostId) return;

                try {
                    const isLiked = likeBtn.querySelector("i").classList.contains("filled");
                    if (isLiked) {
                        await postListService.deletePostLike(bookPostId);
                        likeBtn.querySelector("i").classList.remove("filled");
                        updateLikeCount(likeBtn, -1);
                    } else {
                        await postListService.addPostLike(bookPostId);
                        likeBtn.querySelector("i").classList.add("filled");
                        updateLikeCount(likeBtn, +1);
                    }
                } catch (error) {
                    handleUnauthorized(error);
                }
            };
        });

        // [팔로우] 버튼 클릭 이벤트
        document.querySelectorAll(".card-item .follow-btn").forEach((followBtn) => {
            followBtn.onclick = async (e) => {
                e.preventDefault();
                e.stopPropagation();

                const cardItem = followBtn.closest(".card-item");
                const memberId = cardItem.dataset.memberId;
                if (!memberId) return;

                try {
                    const isFollowing = followBtn.classList.contains("following");
                    if (isFollowing) {
                        await postListService.deleteMemberFollow(memberId);
                        followBtn.classList.remove("following");
                        followBtn.innerText = "팔로우";
                        showToast("팔로우를 취소했습니다.");
                    } else {
                        await postListService.addMemberFollow(memberId);
                        followBtn.classList.add("following");
                        followBtn.innerText = "팔로잉";
                        showToast("팔로우 성공!");
                    }
                } catch (error) {
                    handleUnauthorized(error);
                }
            };
        });

        // [스크랩] 버튼 클릭 이벤트
        document.querySelectorAll(".card-item .add-shelf").forEach((shelfBtn) => {
            shelfBtn.onclick = async (e) => {
                e.preventDefault();
                e.stopPropagation();

                const cardItem = shelfBtn.closest(".card-item");
                const isbn = cardItem.dataset.bookIsbn;
                if (!isbn) return;

                try {
                    const isScrapped = shelfBtn.classList.contains("scrapped");
                    if (isScrapped) {
                        await postListService.deleteScrap(isbn);
                        shelfBtn.classList.remove("scrapped");
                        showToast("스크랩 해제했습니다.");
                    } else {
                        await postListService.addScrap(isbn);
                        shelfBtn.classList.add("scrapped");
                        showToast("스크랩 추가했습니다.");
                    }
                } catch (error) {
                    handleUnauthorized(error);
                }
            };
        });
    }

    // ❤️ 좋아요 숫자 업데이트
    function updateLikeCount(likeBtn, delta) {
        const valueSpan = likeBtn.querySelector(".value");
        if (valueSpan) {
            const count = parseInt(valueSpan.innerText, 10) || 0;
            valueSpan.innerText = count + delta;
        }
    }

    // 🚨 로그인 안 했을 때 처리
    function handleUnauthorized(error) {
        if (error instanceof Response && error.status === 401) {
            alert("로그인이 필요한 서비스입니다.");
            window.location.href = "/personal/login";
        }
    }
});

// 📢 공통 토스트 메시지
function showToast(message) {
    const toast = document.createElement("div");
    toast.className = "toast";
    toast.innerHTML = `<p>${message}</p>`;
    document.querySelector("#wrap").appendChild(toast);
    setTimeout(() => toast.remove(), 2500);
}
