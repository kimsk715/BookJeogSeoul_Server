const searchResultBookService = (() => {
    // 도서 전체 데이터(35개씩 무한스크롤)
    const getBookList = async (keyword, startIndex, maxResults = 35, sort = "Accuracy") => {
        const path = `/search/api/result/books?keyword=${encodeURIComponent(keyword)}&startIndex=${startIndex}&maxResults=${maxResults}&sort=${sort}`;
        const response = await fetch(path);

        const data = await response.json();
        const totalResults = data.totalResults
        const books = data.item;

        return {totalResults, books} // totalResults(총 개수), item[](책 정보 배열)
    }

    return {getBookList : getBookList};
})();