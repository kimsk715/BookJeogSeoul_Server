const bookDetailLayout = (() => {
    const showBookInfo = async (isbn) => {
        // 책 상세 정보 표시를 위한 DOM 요소들
        // const bookAuthor = document.querySelector("#book-author");
        // const bookPublisher = document.querySelector("#book-publisher");
        // const bookPubDate = document.querySelector("#book-pub-date");
        // const bookDescription = document.querySelector("#book-description");
        // const bookCover = document.querySelector("#book-

        const book = await bookDetailService.getBookInfo(isbn);

    }
    return {showBookInfo : showBookInfo};
})();