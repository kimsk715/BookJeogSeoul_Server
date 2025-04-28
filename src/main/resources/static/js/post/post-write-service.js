const postWriteService = (() => {

    // 독후감 쓸 책 검색(api)
    const searchBooks = async (keyword, startIndex = 1) => {
        const response = await fetch(`/book/search?keyword=${encodeURIComponent(keyword)}&start=${startIndex}`);

        const data = await response.json();
        const books = data.item;
        return books;
    }

    // 선택한 도서가 선정도서인지 조회
    const isSelected = async (bookIsbn) => {
        const response = await fetch(`/book/find-selected?bookIsbn=${bookIsbn}`);

        return await response.json();
    }
    return {searchBooks : searchBooks, isSelected : isSelected};
})();