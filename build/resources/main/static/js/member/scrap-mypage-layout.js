const scrapMypageLayout = (() => {

    // 책 목록 초기화
    const clearBookList = () => {
        document.querySelector(".book-list").innerHTML = "";
    };

    // 책 목록 출력
    const renderBookItem = (book) => {
        const container = document.querySelector(".book-list");

        const listItem = document.createElement("li");
        listItem.className = "book-item";

        listItem.innerHTML = `
        <div class="book-wrapper">
            <div class="book-wrapper-inner">
                <div class="book-picture-wrapper">
                    <button class="book-picture-link" data-isbn="${book.bookIsbn}">
                        <div class="book-badges">
                            <div class="book-badges-box" data-isbn="${book.bookIsbn}">
                                <img
                                    src="/images/member/book-like-badge.png"
                                    alt="스크랩"
                                    class="book-badges-image"
                                />
                            </div>
                        </div>
                        <div class="book-picture">
                            <picture class="styled-picture">
                                <img
                                    src="${book.bookCover || '/images/common/default-book-cover.png'}"
                                    alt="${book.bookTitle}"
                                    class="styled-img"
                                />
                            </picture>
                        </div>
                    </button>
                </div>
                <button type="button" class="book-metalink" data-isbn="${book.bookIsbn}">
                    <div class="book-meta">
                        <p class="book-name">${book.bookTitle}</p>
                        <p class="book-author">${book.bookAuthor}</p>
                    </div>
                </button>
            </div>
        </div>
    `;

        container.appendChild(listItem);
    };

    // 데이터가 없을 때 표시
    const renderEmptyMessage = () => {
        const container = document.querySelector(".book-list");
        container.innerHTML = `<li class="empty-message">데이터가 없습니다.</li>`;
    };

    return {clearBookList : clearBookList, renderBookItem : renderBookItem, renderEmptyMessage : renderEmptyMessage}
})();