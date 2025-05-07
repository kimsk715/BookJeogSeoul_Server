const postUpdateService = (() => {

    // 알라딘에서 도서 정보 받아오기
    const getBookInfo = async () => {
        const isbn = document.getElementById("hiddenIsbn").value;
        const response = await fetch(`/book/api/${isbn}`);

        const book = await response.json();
        return book;
    }
    return { getBookInfo : getBookInfo }
})();