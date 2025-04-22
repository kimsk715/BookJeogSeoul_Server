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
        // 독후감 결과가 5개 이하면 이동 막기
        if (posts[0]?.count <= 5) {
            const postLink = document.querySelector(".search-result-book-report>.link.icon-arrow-right");
            if (postLink) {
                postLink.classList.add("disabled");
                postLink.removeAttribute("href"); // 링크 비활성화
            }
        }
    };
    return {showBookList : showBookList, showPostList : showPostList}
})();