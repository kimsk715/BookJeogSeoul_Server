const postWriteLayout = (() => {

    // 검색결과 목록
    const showBookList = async (books) => {
        const container = document.querySelector(".modal-content");
        container.innerHTML = ""; // 기존 목록 초기화

        for (const book of books) {
            // 책 커버 이미지 없으면 알라딘에서 가져오기
            const coverUrl = book.cover || "/images/common/default-book-cover.png";

            const bookItem = document.createElement("li");
            bookItem.className = "user-booklist books-item";
            bookItem.dataset.isbn = book.isbn;
            bookItem.dataset.publisher = book.publisher;
            bookItem.dataset.publishDate = book.publishDate;

            bookItem.innerHTML = `
                <div
                        class="bookjeog-book books only-books"
                    >
                        <a
                            href=""
                            class="book-image"
                        >
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
                                                src="${coverUrl}"
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
                        <a
                            href=""
                            class="book-data"
                        >
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
            `;

            container.appendChild(bookItem);
        }
    }

    // 도서 상세페이지에서 isbn을 받아오면 검색 스킵
    const showPreSelectedBook = (book) => {
        const titleElementTop = document.querySelector(".title.book-name .book-title");
        const titleElementBottom = document.querySelector(".book-container .book-name");
        const produceElement = document.querySelector(".book-container .book-produce");
        const infoElement = document.querySelector(".book-container .book-info");
        const coverImgElement = document.querySelector(".book-container img");
        const bookContainer = document.querySelector(".book-container");

        // insert용 hidden input
        const bookIsbnInput = document.getElementById("hiddenIsbn");
        const hiddenBookTitle = document.getElementById("hiddenBookTitle");

        const title = book.title;
        const author = book.author;
        const publisher = book.publisher || "출판사 없음";
        const publishDate = `${book.publishDate} 출간` || "출간일 정보 없음";
        const cover = book.cover || "/images/common/default-book-cover.png";

        titleElementTop.innerHTML = title;
        titleElementBottom.innerText = title;
        produceElement.innerText = `${author} 지음 · ${publisher}`;
        infoElement.innerText = `${publishDate}`;
        coverImgElement.src = cover;
        bookContainer.style.setProperty("--background-image", `url('${cover}')`);
        bookIsbnInput.value = book.isbn;
        hiddenBookTitle.value = title;
    };
    return { showBookList : showBookList, showPreSelectedBook : showPreSelectedBook };
})();