const searchResultService = (() => {
    // 도서 검색결과 출력
    const getSearchedBooks = async () => {

        // URL에서 keyword 파라미터 추출
        const keyword = new URLSearchParams(window.location.search).get("keyword");

        let path = `/search/api/book-list?keyword=${keyword}`;

        const response = await fetch(path);
        const data = await response.json();

        const totalResults = data.totalResults;
        const books = data.books;

        return { totalResults, books };
    }
    
    // 독후감 검색결과 출력
    const getSearchedPosts = async () => {

        // URL에서 keyword 파라미터 추출
        const keyword = new URLSearchParams(window.location.search).get("keyword");

        let path = `/search/api/book-post-list?keyword=${keyword}`;

        const response = await fetch(path);
        const posts = await response.json();

        return posts;
    }

    // 독후감, 토론방 커버 이미지 가져오기
    const getCoverImages = async (isbn, imageElement) => {
        const response = await fetch(`/book/cover?isbn=${isbn}`)
        const coverUrl = await response.text(); // 서버에서 String으로 받음

        imageElement.src = coverUrl;
    }

    return {getSearchedBooks : getSearchedBooks, getSearchedPosts : getSearchedPosts, getCoverImages : getCoverImages}
})();