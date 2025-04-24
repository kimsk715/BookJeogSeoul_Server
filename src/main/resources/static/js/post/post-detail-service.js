const postDetailService = (() => {
    // isbn으로 알라딘에서 도서 정보 받아오기
    const getBookInfo = async () => {
        // hidden input에서 isbn을 받아옴
        const isbn = document.querySelector("#book-isbn").value;
        const response = await fetch(`/book/api/${isbn}`);

        const book = response.json();
        return book;
    }
    return { getBookInfo : getBookInfo }
})();