const searchResultBookLayout = (function () {

    // 도서 전체 목록 출력(무한스크롤용)
    const showBookList = async (totalResults, books) => {
        // li.books를 감싸는 ul태그
        const bookListContainer = document.querySelector("ul.book-list");
        // 검색 개수 출력하는 곳
        const resultCount = document.querySelector("span.result-count");

        resultCount.innerText = totalResults.toLocaleString(); // 1,352 식으로 출력

        let text = "";
        books.forEach((book) => {
            text += `
                <li>
                    <div class="search-book-list grid">
                        <div class="book-container books">
                            <a href="" class="book-image">
                                <div class="thumbnail">
                                    <div class="thumbnail-inner">
                                        <div class="book-picture">
                                            <picture>
                                                <img
                                                    src="${book.cover}"
                                                    alt="${book.title}"
                                                    style="min-height: 0px;"
                                                />
                                            </picture>
                                        </div>
                                    </div>
                                </div>
                            </a>
                            <a href="" class="book-data">
                                <div class="text-data">
                                    <p class="title">${book.title}</p>
                                    <p class="author">${book.author}</p>
                                </div>
                            </a>
                        </div>
                    </div>
                </li>
            `;
        });

        bookListContainer.innerHTML += text;
    };

    return {
        showBookList: showBookList};
})();