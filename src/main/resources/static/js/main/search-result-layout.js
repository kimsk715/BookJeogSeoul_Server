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
    return {showBookList : showBookList}
})();