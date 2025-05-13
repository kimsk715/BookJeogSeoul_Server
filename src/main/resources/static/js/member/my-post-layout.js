const myPostLayout = (() => {
    const container = document.querySelector(".post-list");

    // ISBN으로 커버 이미지 URL 가져오기
    const fetchBookCover = async (isbn) => {
        try {
            const response = await fetch(`/book/cover?isbn=${isbn}`);
            const coverUrl = await response.text();
            return coverUrl || "/images/common/default_cover.png";
        } catch (error) {
            console.error("Error fetching book cover:", error);
            return "/images/common/default_cover.png";
        }
    };

    // 독후감 아이템 생성
    const createPostItem = async (post) => {
        const li = document.createElement("li");
        li.classList.add("list-item");
        li.setAttribute("data-id", post.bookPostId);

        // 프로필 이미지 경로
        const profileImagePath = (post) => {
            return post.filePath && post.fileName
                ? `/member/profile?path=${post.filePath}&name=${post.fileName}`
                : '/images/common/user_profile_example.png';
        };

        // ISBN으로 커버 이미지 가져오기
        const coverImgSrc = await fetchBookCover(post.bookIsbn);

        li.innerHTML = `
            <div class="content-meta">
                <div class="profile-card-wrapper">
                    <a href="/personal/mypage" class="profile-card-link">
                        <div class="profile-image-wrapper">
                             <img src="${profileImagePath(post)}" 
                                 alt="프로필 이미지" 
                                 class="profile-image" 
                                 width="28" height="28">
                        </div>
                        <div class="profile-nickname-wrapper">
                            <span class="nickname">${post.memberNickName}</span>
                        </div>
                    </a>
                </div>
                <p class="option-data">
                    ${post.createdDate}
                    <span>${post.bookPostIsPublic === "PUBLIC" ? "공개" : "비공개"}</span>
                </p>
            </div>

            <div class="content-card-wrapper">
                <a href="/post/bookpost/${post.bookPostId}" class="content-card-link">
                    <img src="${coverImgSrc}" alt="책 커버 이미지" class="content-image">
                    <div class="summary-wrapper">
                        <h4 class="content-title">${post.bookPostTitle}</h4>
                        <p class="content-summary">${post.bookPostText}</p>
                    </div>
                </a>
            </div>

            <div class="button-wrapper">
                <button class="like-wrapper">
                    <span class="like-label">좋아요</span>
                    <span class="like-count">${post.likeCount}</span>
                </button>
                <button class="more-button" data-id="${post.bookPostId}" onclick="openModal(this)">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" viewBox="0 0 24 25" class="mds-icon" color="var(--text-01)">
                        <path d="M13 6.245a1 1 0 1 1-2 0 1 1 0 0 1 2 0M13 12.245a1 1 0 1 1-2 0 1 1 0 0 1 2 0M12 19.245a1 1 0 1 0 0-2 1 1 0 0 0 0 2" fill="currentColor"></path>
                    </svg>
                </button>
            </div>
        `;

        return li;
    };

    // 독후감 목록 렌더링
    const renderPosts = async (posts, reset = false) => {
        if (reset) container.innerHTML = "";
        for (const post of posts) {
            const postItem = await createPostItem(post);
            container.appendChild(postItem);
        }
    };

    return {
        renderPosts,
    };
})();
