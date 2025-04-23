const searchResultLayout = (() => {
    const showBookList = async (totalResults, books) => {
        const bookListContainer = document.querySelector("ul.book-list");
        const resultCount = document.querySelector("small.book-count");

        // 총 검색 결과 개수 표시
        resultCount.innerText = "+" + totalResults;

        let text = ``;
        // 책 개수만큼 반복해서 실행
        books.forEach((book) => {

            text += `
                 <li>
                    <div class="search-book-list grid">
                        <div
                            class="book-container books"
                        >
                            <a
                                href="/book/detail/${book.isbn13}"
                                class="book-image"
                            >
                                <div class="thumbnail">
                                    <div
                                        class="thumbnail-inner"
                                    >
                                        <div
                                            class="book-picture"
                                        >
                                            <picture>
                                                <img
                                                    src="${book.cover}"
                                                    alt="${book.title}"
                                                    style="
                                                        min-height: 0px;
                                                    "
                                                />
                                            </picture>
                                        </div>
                                    </div>
                                </div>
                            </a>
                            <a
                                href="/book/detail/${book.isbn13}"
                                class="book-data"
                            >
                                <div class="text-data">
                                    <p class="title">
                                        ${book.title}
                                    </p>
                                    <p class="author">
                                        ${book.author}
                                    </p>
                                </div>
                            </a>
                        </div>
                    </div>
                </li>
            `;
        })

        bookListContainer.innerHTML = text;

        text = ``;
        // 책 결과가 5개 이하면 이동 막기
        if (books.length <= 5) {
            const bookLink = document.querySelector(".search-result-book>.link.icon-arrow-right");
            if (bookLink) {
                bookLink.classList.add("disabled");
                bookLink.removeAttribute("href"); // 링크 비활성화
            }
        }
    }

    // 독후감 목록
    const showPostList = async (posts) => {
        const postListContainer = document.querySelector("ul.book-report");
        const resultCount = document.querySelector("small.book-report-count");

        // 총 검색 결과 개수 표시
        resultCount.innerText = posts[0]?.count || 0;

        postListContainer.innerHTML = "";

        posts.forEach((post) => {

            const li = document.createElement("li");
            li.className = "slide-item";

            // 독후감 작성자 이미지
            const imageUrl = (post.filePath && post.fileName)
                ? `/member/profile?path=${post.filePath.replace("C:\\upload\\", "").replace(/\\/g, "/")}&name=${post.fileName}`
                : "/images/common/user-profile-example.png";

            li.innerHTML = `
            <div class="search-book-report-list">
                <a href="" class="book-report-inner">
                    <div class="book-report-data">
                        <div class="book-image">
                            <div class="book-container books">
                                <div class="book-image">
                                    <div class="thumbnail">
                                        <div class="thumbnail-inner">
                                            <picture>
                                                <img
                                                    src="/images/common/default-book-cover.png"
                                                    alt="도서 커버"
                                                    class="book-cover"
                                                    data-isbn="${post.bookIsbn}"
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
                <a href="" class="author">
                    <div class="image" style="background-image: url('${imageUrl}');"></div>
                    <div class="info">
                        <span class="name">${post.memberNickName}</span>
                        <p class="regdate">${post.createdDate}</p>
                    </div>
                </a>
            </div>
        `;

            postListContainer.appendChild(li);

            // 여기서 cover 이미지 동적으로 세팅
            const img = li.querySelector("img.book-cover"); // 책 이미지
            const bookImageWrapper = li.querySelector(".book-report-data > .book-image"); // 책 배경이미지

            if (img && post.bookIsbn) {
                searchResultService.getCoverImages(post.bookIsbn, img)
                  .then(() => {
                    const imageUrl = img.src;
                    bookImageWrapper.style.setProperty("--background-image", `url('${imageUrl}')`);
                });
            }
        });
        // 독후감 결과가 3개 이하면 이동 막기
        if (posts[0]?.count <= 3) {
            const postLink = document.querySelector(".search-result-book-report>.link.icon-arrow-right");
            if (postLink) {
                postLink.classList.add("disabled");
                postLink.removeAttribute("href"); // 링크 비활성화
            }
        }
    };

    // 토론글 목록
    const showDiscussionList = async (totalCount, discussions) => {
        const discussionListContainer = document.querySelector("ul.discussion");
        const resultCount = document.querySelector("small.discussion-count");
        console.log(resultCount, totalCount);

        // 총 검색 결과 수 표시
        resultCount.innerText = totalCount || 0;

        // 기존 목록 초기화
        discussionListContainer.innerHTML = "";

        // for문으로 반복(forEach는 await을 쓰기 부적절함)
        for (const discussion of discussions) {
            const formattedDate = discussion.createdDate.split(" ")[0];

            const li = document.createElement("li");
            li.className = "slide-item";

            li.innerHTML = `
                            <div
                                    class="search-discussion-list"
                                >
                                    <a
                                        href=""
                                        class="discussion-inner"
                                    >
                                        <div
                                            class="discussion-data"
                                        >
                                            <div
                                                class="book-image book-background"
                                            >
                                                <div
                                                    class="book-container books"
                                                >
                                                    <div
                                                        class="book-image book-foreground"
                                                    >
                                                        <div
                                                            class="thumbnail"
                                                        >
                                                            <div
                                                                class="thumbnail-inner"
                                                            >
                                                                <picture>
                                                                    <img
                                                                        src="/images/common/default-book-cover.png"
                                                                        alt="도서 커버"
                                                                        style="
                                                                            min-height: 0;
                                                                        "
                                                                         class="book-cover"
                                                                    />
                                                                </picture>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div
                                                class="data-text"
                                            >
                                                <span
                                                    class="title"
                                                    >${discussion.discussionTitle}</span
                                                >
                                                <p
                                                    class="content bookType"
                                                >
                                                    ${discussion.discussionText}
                                                </p>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="" class="author">
                                        <div class="info">
                                            <span class="name"
                                                >작성된 댓글
                                                개수: ${discussion.commentCount || 0}개</span
                                            >
                                            <p class="regdate">
                                                ${formattedDate}
                                            </p>
                                        </div>
                                    </a>
                                </div>
        `;

            discussionListContainer.appendChild(li);

            const img = li.querySelector("img.book-cover");
            const bookImageWrapper = li.querySelector(".book-image.book-background");

            if (discussion.bookIsbn) {
                try {
                    // img가 있을 경우에만 두 번째 인자로 전달
                    const coverUrl = await searchResultService.getCoverImages(discussion.bookIsbn, img || null);

                    // 배경 이미지는 별도로 적용
                    if (bookImageWrapper) {
                        bookImageWrapper.style.setProperty("--background-image", `url('${coverUrl}')`);
                    }
                } catch (error) {
                    console.error("커버 이미지 로딩 실패:", error);
                }
            }
        }

        // 결과가 3개 이하이면 전체보기 링크 비활성화
        if (totalCount <= 3) {
            const discussionLink = document.querySelector(".search-result-discussion > .link.icon-arrow-right");
            if (discussionLink) {
                discussionLink.classList.add("disabled");
                discussionLink.removeAttribute("href");
            }
        }
    };


    // 기부 단체 목록
    const showSponsorList = async (totalCount, sponsors) => {
        const sponsorListContainer = document.querySelector("ul.organization");
        const resultCount = document.querySelector("small.organization-count");

        // 총 검색 결과 개수 표시
        resultCount.innerText = totalCount.toLocaleString();

        sponsorListContainer.innerHTML = "";

        sponsors.forEach((sponsor) => {
            const li = document.createElement("li");
            li.className = "slide-item";

            const imageUrl = (sponsor.filePath && sponsor.fileName)
                ? `/member/profile?path=${sponsor.filePath.replace("C:\\upload\\", "").replace(/\\/g, "/")}&name=${sponsor.fileName}`
                : "/images/common/user-profile-example.png";

            li.innerHTML = `
            <div class="search-organization-list">
                <a href="" class="organization-inner">
                    <div class="organization-data">
                        <div class="organization-image"></div>
                        <div class="organization-text">
                            <span class="title">${sponsor.sponsorName}</span>
                            <div class="line"></div>
                            <p class="content">
                                ${sponsor.sponsorMainAddress || ''}
                                ${sponsor.sponsorSubAddress || ''}<br/>
                                ${sponsor.sponsorPhoneNumber || ''}
                            </p>
                        </div>
                    </div>
                </a>
            </div>
        `;

            // 이미지 넣기 (.organization-image::before에서 쓰는 CSS 변수 설정)
            li.querySelector(".organization-image")
                .style.setProperty("--background-image", `url(${imageUrl})`);

            sponsorListContainer.appendChild(li);
        });
        // 단체 결과가 3개 이하면 이동 막기
        if (totalCount <= 3) {
            const sponsorLink = document.querySelector(".result-organization-more>.link.icon-arrow-right");
            if (sponsorLink) {
                sponsorLink.classList.add("disabled");
                sponsorLink.removeAttribute("href"); // 링크 비활성화
            }
        }
    };
    return {showBookList : showBookList, showPostList : showPostList, showDiscussionList : showDiscussionList, showSponsorList : showSponsorList}
})();