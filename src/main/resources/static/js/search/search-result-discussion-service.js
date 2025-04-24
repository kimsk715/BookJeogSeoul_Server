const searchResultDiscussionService = (() => {
    // 토론글 전체 데이터(8개씩 무한스크롤)
    const getDiscussionList = async (keyword, offset = 0, sortType = "new") => {
        const response = await fetch(`/search/api/result/discussions?keyword=${keyword}&offset=${offset}&sortType=${sortType}`)
        const data = await response.json();

        const totalCount = data.totalCount;
        const discussions = data.discussions;
        console.log(totalCount, discussions);

        return { totalCount, discussions }
    }

    // 토론방 커버 이미지 가져오기
    const getCoverImages = async (isbn, imageElement) => {
        try {
            const response = await fetch(`/book/cover?isbn=${isbn}`);
            const coverUrl = await response.text();

            if (imageElement) {
                imageElement.src = coverUrl;
            }

            return coverUrl;
        } catch (error) {
            const fallbackUrl = "/images/common/default-book-cover.png";

            if (imageElement) {
                imageElement.src = fallbackUrl;
            }

            return fallbackUrl;
        }
    };

    return {getDiscussionList : getDiscussionList, getCoverImages : getCoverImages};
})();