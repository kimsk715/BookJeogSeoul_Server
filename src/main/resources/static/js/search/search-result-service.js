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
        try {
            const response = await fetch(`/book/cover?isbn=${isbn}`);
            const coverUrl = await response.text();
            imageElement.src = coverUrl;
            return coverUrl;
        } catch (error) {
            imageElement.src = "/images/common/default-book-cover.png";
            if (imageElement) {
                imageElement.src = fallbackUrl;
            }
            return fallbackUrl;
        }
    };

    // 토론방 검색결과 출력
    const getSearchedDiscussions = async () => {
        // URL에서 keyword 파라미터 추출
        const keyword = new URLSearchParams(window.location.search).get("keyword");

        let path = `/search/api/discussion-list?keyword=${keyword}`;

        const response = await fetch(path);
        const data = await response.json();
        console.log("✅ API 응답 data:", data);

        const totalCount = data.totalCount;
        const discussions = data.discussions;

        return {totalCount, discussions};
    }

    // 기부단체 검색결과 출력
    const getSearchedSponsors = async () => {
        const keyword = new URLSearchParams(window.location.search).get("keyword");

        let path = `/search/api/sponsor-list?keyword=${keyword}`;

        const response = await fetch(path);
        const data = await response.json();

        const totalCount = data.totalCount;
        const sponsors = data.sponsors;

        return {totalCount, sponsors};
    }

    // 기부글 검색결과 출력
    const getSearchedReceivers = async () => {
        const keyword = new URLSearchParams(window.location.search).get("keyword");

        let path = `/search/api/receiver-list?keyword=${keyword}`;

        const response = await fetch(path);
        const data = await response.json();

        const totalCount = data.totalCount;
        const receivers = data.receivers;

        return {totalCount, receivers};
    }

    return {getSearchedBooks : getSearchedBooks, getSearchedPosts : getSearchedPosts, getCoverImages : getCoverImages, getSearchedDiscussions : getSearchedDiscussions, getSearchedSponsors : getSearchedSponsors, getSearchedReceivers : getSearchedReceivers}
})();