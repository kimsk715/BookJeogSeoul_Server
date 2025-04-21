const bookDetailLayout = (() => {
    // 이 책의 스크랩 개수 조회
    const showScrapCount = async () => {
        const count = await bookDetailService.getScrapCount();
        document.querySelector(".scrapCount").innerText = count + "+";
    }

    // 이 작가의 다른 책들 출력
    const showAuthorBooks = async (itemListData) => {
        // js로 생성될 책을 넣을 리스트
        const authorBooksUl = document.querySelector(".book-shelf ul.slide-wrapper");

        let text = ``;
        // 책 개수만큼 반복해서 li 생성
        itemListData.forEach((book) => {
            text += `
                <li class="slide-item">
                    <div
                        class="bookjeog-book books"
                    >
                         <a href="/book/detail/${book.isbn13}" class="book-image">
                            <div
                                class="thumbnail"
                            >
                                <div
                                    class="thumbnail-inner"
                                >
                                    <div
                                        class="book-picture"
                                    >
                                        <picture>
                                            <img
                                                src="${book.cover}"
                                                alt="도서 썸네일"
                                                width="145"
                                                style="
                                                    min-height: 0;
                                                "
                                            />
                                        </picture>
                                    </div>
                                </div>
                            </div>
                        </a>
                       <a href="/book/detail/${book.isbn13}" class="book-data">
                            <div
                                class="metadata"
                            >
                                <p
                                    class="title"
                                >
                                    ${book.title}
                                </p>
                                <p
                                    class="author"
                                >
                                    ${book.author}
                                </p>
                            </div>
                        </a>
                    </div>
                </li>
            `;
        })

        authorBooksUl.innerHTML = text;

        text = ``;
    }

    // 이 책의 독후감들 출력
    const showThisBookPosts = async (bookPostListData) => {
        // 이거 넣을 곳
        const bookPostsUl = document.querySelector("ul.report-list");
        const isbn = window.location.pathname.split("/").pop();

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
                    <button type="button" class="gtm-report-follow" id="${post.memberId}">
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
        const count = await bookDetailService.getThisBookPostsCount();
        const countP = document.querySelector("span.number");

        countP.textContent = count;

        // 독후감이 0개라면 이 책의 독후감 탭 노출 시키지 않기
        // 독후감 개수가 5개 이하면 이 책의 독후감 전체 페이지 버튼 노출시키지 않기
        if(count === 0) {
            document.querySelector("#anchorReport").style.display = "none";
            } else if (count < 6){
                document.querySelector("#thisBookPostsBtn").style.background = "none";
            document.querySelector("#thisBookPostsBtn").style.pointerEvents = "none";
            } else {
            // 독후감이 6개 이상일 때만 링크 클릭 가능
            const isbn = window.location.pathname.split("/").pop();
            document.querySelector("#thisBookPostsBtn").href = `/post/book/post-list/${isbn}`;
        }
    };
    
    return {showScrapCount : showScrapCount, showAuthorBooks: showAuthorBooks, showThisBookPosts : showThisBookPosts, showPostCount : showPostCount};
})();