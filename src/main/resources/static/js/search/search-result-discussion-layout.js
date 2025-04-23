const searchResultDiscussionLayout = (() => {
    const showDiscussionList = async (totalCount, discussions) => {
        const discussionListContainer = document.querySelector("ul.discussion");
        const resultCount = document.querySelector("span.result-count");

        // 총 개수 표시
        resultCount.innerText = totalCount || 0;

        if (currentPage === 1) {
            discussionListContainer.innerHTML = "";
        }

        for (const discussion of discussions) {
            const formattedDate = discussion.createdDate.split(" ")[0];

            const li = document.createElement("li");
            li.className = "slide-item";

            li.innerHTML = `
                <div class="search-discussion-list">
                    <a href="#" class="discussion-inner">
                        <div class="discussion-data">
                            <div class="book-image book-background">
                                <div class="book-container books">
                                    <div class="book-image book-foreground">
                                        <div class="thumbnail">
                                            <div class="thumbnail-inner">
                                                <picture>
                                                    <img
                                                        src="/images/common/default-book-cover.png"
                                                        alt="${discussion.bookTitle}"
                                                        class="book-cover"
                                                        data-isbn="${discussion.bookIsbn}"
                                                        style="min-height: 0;"
                                                    />
                                                </picture>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="data-text">
                                <span class="title">${discussion.discussionTitle}</span>
                                <p class="content bookType">${discussion.discussionText}</p>
                            </div>
                        </div>
                    </a>
                    <a href="#" class="author">
                        <div class="info">
                            <span class="name">작성된 댓글 개수: ${discussion.commentCount || 0}개</span>
                            <p class="regdate">${formattedDate}</p>
                        </div>
                    </a>
                </div>
            `;

            discussionListContainer.appendChild(li);

            // 커버 이미지 fetch → img와 background 모두 설정
            const bookImageWrapper = li.querySelector(".book-image.book-background");
            const img = li.querySelector("img.book-cover");

            if (discussion.bookIsbn && img && bookImageWrapper) {
                try {
                    const coverUrl = await searchResultDiscussionService.getCoverImages(discussion.bookIsbn, img);
                    bookImageWrapper.style.setProperty("--background-image", `url('${coverUrl}')`);
                } catch (error) {
                    console.error("커버 이미지 로딩 실패:", error);
                }
            }
        }

        // 결과가 3개 이하일 때 전체보기 링크 비활성화
        if (totalCount <= 3) {
            const discussionLink = document.querySelector(".search-result-discussion > .link.icon-arrow-right");
            if (discussionLink) {
                discussionLink.classList.add("disabled");
                discussionLink.removeAttribute("href");
            }
        }
    };

    return { showDiscussionList : showDiscussionList};
})();
