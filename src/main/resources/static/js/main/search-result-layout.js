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
    }

    // 독후감 목록
    const showPostList = async (posts) => {
        const postListContainer = document.querySelector("ul.book-report");

        let text = ``;
        // 책 개수만큼 반복해서 실행
        posts.forEach((post) => {
            let imageUrl = "";

            if (post.filePath && post.fileName) {
                const rawPath = post.filePath; // 기본 경로
                const relativePath = rawPath.replace("C:\\upload\\", "").replace(/\\/g, "/"); // 윈도우 경로를 웹 경로로
                imageUrl = `/member/profile?path=${relativePath}&name=${post.fileName}`;
            } else {
                imageUrl = "/images/common/default-profile.png";
            }

            text += `
                 <li class="slide-item">
                    <div
                        class="search-book-report-list"
                    >
                        <a
                            href=""
                            class="book-report-inner"
                        >
                            <div
                                class="book-report-data"
                            >
                                <div
                                    class="book-image"
                                >
                                    <div
                                        class="book-container books"
                                    >
                                        <div
                                            class="book-image"
                                        >
                                            <div
                                                class="thumbnail"
                                            >
                                                <div
                                                    class="thumbnail-inner"
                                                >
                                                    <picture>
                                                        <img
                                                            src="https://cover.millie.co.kr/service/cover/15067573/fde9dd87449f4f8d9289583ab26c507e.jpg?w=145&f=webp&q=80"
                                                            alt=""
                                                            style="
                                                                min-height: 0;
                                                            "
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
                                        >${post.bookPostTitle}</span
                                    >
                                    <p
                                        class="content bookType"
                                    >
                                        ${post.bookPostText}
                                    </p>
                                </div>
                            </div>
                        </a>
                        <a href="" class="author">
                            <div
                                class="image"
                                style="
                                    background-image: url('${imageUrl}');
                                "
                            ></div>
                            <div class="info">
                                <span class="name"
                                    >${post.memberNickName}</span
                                >
                                <p class="regdate">
                                    ${post.createdDate}
                                </p>
                            </div>
                        </a>
                    </div>
                </li>
            `;
        })

        postListContainer.innerHTML = text;

        text = ``;
    }
    return {showBookList : showBookList, showPostList : showPostList}
})();