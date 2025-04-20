const bookDetailPostLayout = (() => {

    // 이 책의 독후감들 출력
    const showThisBookAllPosts = async (bookPostListData) => {
        // 이거 넣을 곳
        const bookPostsUl = document.querySelector("ul.post-list");

        let text = ``;
        // 독후감 개수만큼 반복해서 li 생성
        bookPostListData.forEach((post) => {
            let imageUrl = "";

            if (post.filePath && post.fileName) {
                const rawPath = post.filePath; // 기본 경로
                const relativePath = rawPath.replace("C:\\upload\\", "").replace(/\\/g, "/"); // 윈도우 경로를 웹 경로로
                imageUrl = `/member/profile?path=${relativePath}&name=${post.fileName}`;
            } else {
                imageUrl = "/images/common/default-profile.png";
            }

            text += `
            <li>
                <a href="" class="post-cont">
                    <p class="title">
                        ${post.bookPostTitle}
                    </p>
                    <p class="cont">
                        ${post.bookPostText}
                    </p>
                </a>
                <div class="profile">
                    <a class="inner gtm-post-lib">
                        <div
                            class="image"
                            style="
                                background-image: url('${imageUrl}');
                            "
                        ></div>
                        <p class="name">${post.memberNickName}</p>
                    </a>
                    <button
                        type="button"
                        class="gtm-post-follow"
                        id="${post.memberId}"
                    >
                        팔로우
                    </button>
                </div>
            </li>
            `;
        })

        bookPostsUl.innerHTML = text;

        text = ``;
    }

    // 이 책의 전체 독후감들 개수 출력
    const showPostCount = async () => {
        const count = await bookDetailPostService.getThisBookPostsCount();
        const countP = document.querySelector(".post-content>.count");

        countP.textContent = "총 " + count + "개";
    };

    return {showThisBookAllPosts : showThisBookAllPosts, showPostCount : showPostCount};
})();