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
    
    return {showScrapCount : showScrapCount, showAuthorBooks: showAuthorBooks};
})();