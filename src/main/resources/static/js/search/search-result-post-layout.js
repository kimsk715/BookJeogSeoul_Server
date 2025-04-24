const searchResultPostLayout = (() => {

    // 독후감 전체 목록 출력 (무한스크롤용)
    const showPostList = async (totalCount, posts) => {
        const postListContainer = document.querySelector("ul.book-report");
        const resultCount = document.querySelector("span.result-count");
        console.log("totalCount:", totalCount);
        console.log("posts:", posts);

        // 총 검색 결과 수 표시 (예: 1,234)
        resultCount.innerText = totalCount.toLocaleString();

        let text = "";
        posts.forEach((post) => {
            const profileImg = (post.filePath && post.fileName)
                ? `/member/profile?path=${post.filePath.replace("C:\\upload\\", "").replace(/\\/g, "/")}&name=${post.fileName}`
                : "/images/common/user-profile-example.png";

            const formattedDate = post.createdDate.split(" ")[0];

            text += `
                <li class="slide-item">
                    <div class="search-book-report-list">
                        <a href="#" class="book-report-inner">
                            <div class="book-report-data" >
                                <div class="book-image" style="--background-image: url('${post.cover}')">
                                    <div class="book-container books">
                                        <div class="book-image">
                                            <div class="thumbnail">
                                                <div class="thumbnail-inner">
                                                    <picture>
                                                        <img
                                                            src="${post.cover}"
                                                            alt="${post.bookPostTitle}"
                                                            class="book-cover"
                                                            data-isbn="${post.bookIsbn}"
                                                            style="min-height: 0;"
                                                        />
                                                    </picture>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="data-text">
                                    <span class="title">${post.bookPostTitle}</span>
                                    <p class="content bookType">${post.bookPostText}</p>
                                </div>
                            </div>
                        </a>
                        <a href="#" class="author">
                            <div class="image" style="background-image: url('${profileImg}');"></div>
                            <div class="info">
                                <span class="name">${post.memberNickName}</span>
                                <p class="regdate">${formattedDate}</p>
                            </div>
                        </a>
                    </div>
                </li>
            `;
        });

        postListContainer.innerHTML += text;

        // 새로 추가된 DOM 요소에서 이미지 커버 설정
        const newCovers = postListContainer.querySelectorAll("img.book-cover");
        newCovers.forEach(img => {
            const isbn = img.dataset.isbn;
            if (isbn) {
                searchResultPostService.getCoverImages(isbn, img);
            }
        });
    };

    return {showPostList: showPostList};
})();