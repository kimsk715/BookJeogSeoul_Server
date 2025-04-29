const postListLayout = (() => {
    let currentMode = "all"; // 현재 모드 ("all" | "following")

    const recommendList = document.querySelector(".card-list.recommend");
    const followingList = document.querySelector(".card-list.following");

    const getMode = () => currentMode;

    // 독후감 카드 출력
    const showPostList = async (posts, reset = false) => {
        const container = currentMode === "all" ? recommendList : followingList;
        const otherContainer = currentMode === "all" ? followingList : recommendList; // 반대쪽 div 찾기

        if (reset) {
            container.innerHTML = "";
            otherContainer.innerHTML = ""; // 반대쪽 div도 비워
            container.style.display = "grid"; // 내가 보여줄 div
            otherContainer.style.display = "none"; // 반대 div 숨기기
        }

        for (const post of posts) {
            // 책 커버 이미지 없으면 알라딘에서 가져오기
            if (!post.cover && post.bookIsbn) {
                post.cover = await postListService.getCoverImages(post.bookIsbn);
            }

            const profileImg = (post.filePath && post.fileName)
                ? `/member/profile?path=${post.filePath.replace("C:\\upload\\", "").replace(/\\/g, "/")}&name=${post.fileName}`
                : "/images/common/user-profile-example.png";

            const formattedTime = formatTime(post.createdDate);
            const coverUrl = post.cover || "/images/common/default-book-cover.png";
            const bookPostLink = `/post/bookpost/${post.bookPostId}`;

            const cardItem = document.createElement("div");
            cardItem.className = "card-item";

            // 각 cardItem에 데이터 저장 (좋아요, 팔로우, 스크랩용)
            cardItem.dataset.bookPostId = post.bookPostId;
            cardItem.dataset.memberId = post.memberId;
            cardItem.dataset.bookIsbn = post.bookIsbn;

            cardItem.innerHTML = `
                <div class="user diary-theme">
                    <a href="#" class="profile-image" style="background-image: url('${profileImg}');"></a>
                    <div class="metadata">
                        <a href="#" class="nickname-link"><span class="nickname">${post.memberName}</span></a>
                        <div><span class="time">${formattedTime}</span></div>
                    </div>
                    <button type="button" class="button follow-btn">팔로우</button>
                </div>

                <div class="post">
                    <a href="${bookPostLink}" class="box book-image" style="--background-image: url('${coverUrl}');">
                        <div class="book-picture">
                            <picture>
                                <img src="${coverUrl}" alt="${post.bookPostTitle}">
                            </picture>
                        </div>
                    </a>
                    <div class="metadata content">
                        <a href="${bookPostLink}" class="inner">
                            <p class="title">${post.bookPostTitle}</p>
                            <p class="sub-title" style="min-height: 48px;">${post.bookPostText}</p>
                        </a>
                    </div>
                </div>

                <div class="bottom">
                    <button type="button" class="btn add-shelf"><i class="mds-icon-add-shelf"></i>담기</button>
                    <button type="button" class="btn like">
                        <i class="mds-icon-heart"></i> <!-- 좋아요는 기본 빈 하트 -->
                        <span class="label">좋아요</span>
                        <span class="value">${post.likeCount || 0}</span>
                    </button>
                </div>
            `;

            container.appendChild(cardItem);

            await initializeButtonStates(cardItem, post);

        }
    }

    // 처음 팔로우, 좋아요, 스크랩 상태 반영
    async function initializeButtonStates(cardItem, post) {
        console.log("✅ 초기 버튼 상태 설정 시작", post.bookPostId);

        const likeButton = cardItem.querySelector(".btn.like i");
        const followButton = cardItem.querySelector(".follow-btn");
        const shelfButton = cardItem.querySelector(".btn.add-shelf");

        try {
            const [liked, followed, scrapped] = await Promise.all([
                postListService.checkMyPostLike(post.bookPostId),
                postListService.checkMemberFollow(post.memberId),
                postListService.checkScrap(post.bookIsbn)
            ]);

            console.log("초기 상태:", { liked, followed, scrapped });

            if (liked && likeButton) {
                likeButton.classList.add("filled");
            }
            if (followed && followButton) {
                followButton.classList.add("following");
                followButton.innerText = "팔로잉";
            }
            if (scrapped && shelfButton) {
                shelfButton.classList.add("scrapped");
            }
        } catch (error) {
            console.error("초기 버튼 상태 동기화 실패:", error);
        }
    }

    // 작성시간 → "n분 전 / n시간 전 / n일 전" 포맷 변환
    const formatTime = (createdDate) => {
        const created = new Date(createdDate);
        const now = new Date();
        const diffMinutes = Math.floor((now - created) / (1000 * 60));

        if (diffMinutes < 1) return "방금 전";
        if (diffMinutes < 60) return `${diffMinutes}분 전`;
        if (diffMinutes < 60 * 24) return `${Math.floor(diffMinutes / 60)}시간 전`;
        return `${Math.floor(diffMinutes / (60 * 24))}일 전`;
    };

    // 현재 모드 세팅
    const setMode = (mode) => {
        currentMode = mode;
    };

    return {
        showPostList: showPostList,
        setMode: setMode,
        getMode: getMode,
    };
})();
