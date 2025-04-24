const bookDetailPostLayout = (() => {

    // 이 책의 독후감들 무한스크롤로 출력
    const showThisBookAllPosts = async (posts) => {
        const bookPostsUl = document.querySelector("ul.post-list");

        posts.forEach((post) => {
            let imageUrl = "";

            if (post.filePath && post.fileName) {
                const rawPath = post.filePath;
                const relativePath = rawPath.replace("C:\\upload\\", "").replace(/\\/g, "/");
                imageUrl = `/member/profile?path=${relativePath}&name=${post.fileName}`;
            } else {
                imageUrl = "/images/common/default-profile.png";
            }

            const li = document.createElement("li");
            li.innerHTML = `
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
                    <div class="image" style="background-image: url('${imageUrl}');"></div>
                    <p class="name">${post.memberNickName}</p>
                </a>
                <button type="button" class="gtm-post-follow" id="${post.memberId}">
                    팔로우
                </button>
            </div>
        `;

            bookPostsUl.append(li); // append를 써서 만든 li를 추가함
        });
    };

    // 이 책의 전체 독후감들 개수 출력
    const showPostCount = async () => {
        const count = await bookDetailPostService.getThisBookPostsCount();
        const countP = document.querySelector(".post-content>.count");

        countP.textContent = "총 " + count + "개";
    };

    return {showThisBookAllPosts : showThisBookAllPosts, showPostCount : showPostCount};
})();