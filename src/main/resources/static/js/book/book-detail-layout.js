const bookDetailLayout = (() => {
    // 이 책의 스크랩 개수 조회
    const showScrapCount = async () => {
        const count = await bookDetailService.getScrapCount();
        document.querySelector(".scrapCount").innerText = count + "+";
    };

    // 이 작가의 다른 책들 출력
    const showAuthorBooks = async (itemListData) => {
        const authorBooksUl = document.querySelector(".book-shelf ul.slide-wrapper");
        let text = ``;

        itemListData.forEach((book) => {
            text += `
                <li class="slide-item">
                    <div class="bookjeog-book books">
                        <a href="/book/detail/${book.isbn13}" class="book-image">
                            <div class="thumbnail">
                                <div class="thumbnail-inner">
                                    <div class="book-picture">
                                        <picture>
                                            <img src="${book.cover}" alt="도서 썸네일" width="145" style="min-height: 0;" />
                                        </picture>
                                    </div>
                                </div>
                            </div>
                        </a>
                        <a href="/book/detail/${book.isbn13}" class="book-data">
                            <div class="metadata">
                                <p class="title">${book.title}</p>
                                <p class="author">${book.author}</p>
                            </div>
                        </a>
                    </div>
                </li>
            `;
        });

        authorBooksUl.innerHTML = text;
    };

    // AI 추천 도서 출력
    // 로그인 정보가 없으면 알라딘 인기도서 출력
    const showRecommendedBooks = (bookList, type = "ai") => {
        const wrapper = document.querySelector(".book-shelf.recommend .slide-wrapper");
        const titleAnchor = document.querySelector(".book-info-title.link.recommend a");

        if (!wrapper) return;

        // 제목 텍스트 변경
        if (titleAnchor) {
            titleAnchor.textContent = type === "bestseller"
                ? "지금 인기 있는 책"
                : "AI가 추천하는 비슷한 책";
        }

        let text = ``;

        bookList.forEach((book) => {
            text += `
            <li class="slide-item">
                <div class="bookjeog-book books">
                    <a href="/book/detail/${book.isbn}" class="book-image">
                        <div class="thumbnail">
                            <div class="thumbnail-inner">
                                <div class="book-picture">
                                    <picture>
                                        <img src="${book.cover}" alt="도서 썸네일" width="145" style="min-height: 0;" />
                                    </picture>
                                </div>
                            </div>
                        </div>
                    </a>
                    <a href="/book/detail/${book.isbn}" class="book-data">
                        <div class="metadata">
                            <p class="title">${book.title}</p>
                            <p class="author">${book.author}</p>
                        </div>
                    </a>
                </div>
            </li>
        `;
        });

        wrapper.innerHTML = text;
    };

    // 이 책의 독후감들 출력
    const showThisBookPosts = async (bookPostListData) => {
        const bookPostsUl = document.querySelector("ul.report-list");
        const isbn = window.location.pathname.split("/").pop();

        let text = ``;
        bookPostListData.forEach((post) => {
            let imageUrl = "";

            if (post.filePath && post.fileName) {
                const rawPath = post.filePath;
                const relativePath = rawPath.replace("C:\\upload\\", "").replace(/\\/g, "/");
                imageUrl = `/member/profile?path=${relativePath}&name=${post.fileName}`;
            } else {
                imageUrl = "/images/common/default-profile.png";
            }

            text += `
                <li class="slide-item">
                    <a href="" class="report-cont">
                        <p class="title">${post.bookPostTitle}</p>
                        <p class="cont">${post.bookPostText}</p>
                    </a>
                    <div class="profile">
                        <a href="" class="inner gtm-report-lib">
                            <div class="image" style="background-image: url('${imageUrl}');"></div>
                            <p class="name">${post.memberNickName}</p>
                        </a>
                        <button type="button" class="gtm-report-follow" id="${post.memberId}">팔로우</button>
                    </div>
                </li>
            `;
        });

        bookPostsUl.innerHTML = text;
    };

    // 이 책의 전체 독후감들 개수 출력
    const showPostCount = async () => {
        const count = await bookDetailService.getThisBookPostsCount();
        const countP = document.querySelector("span.number");
        countP.textContent = count;

        if (count === 0) {
            document.querySelector("#anchorReport").style.display = "none";
        } else if (count < 6) {
            document.querySelector("#thisBookPostsBtn").style.background = "none";
            document.querySelector("#thisBookPostsBtn").style.pointerEvents = "none";
        } else {
            const isbn = window.location.pathname.split("/").pop();
            document.querySelector("#thisBookPostsBtn").href = `/book/post-list/${isbn}`;
        }
    };

    return {
        showScrapCount : showScrapCount,
        showAuthorBooks : showAuthorBooks,
        showThisBookPosts : showThisBookPosts,
        showPostCount : showPostCount,
        showRecommendedBooks : showRecommendedBooks
    };
})();
