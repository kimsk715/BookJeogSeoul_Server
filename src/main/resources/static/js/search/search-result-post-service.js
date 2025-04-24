const searchResultPostService = (() => {
    // 도서 전체 데이터(8개씩 무한스크롤)
    const getPostList = async (keyword, offset = 0, sortType = "new") => {
        try {
            const response = await fetch(`/search/api/result/book-posts?keyword=${encodeURIComponent(keyword)}&offset=${offset}&sortType=${sortType}`);
            if (!response.ok) {
                throw new Error("서버 응답 실패");
            }

            const data = await response.json(); // { posts: [...], totalCount: 123 }
            const posts = data.posts || [];
            const totalCount = data.totalCount || 0;

            // 각 post에 커버 이미지 경로 집어넣기
            const postsWithCovers = await Promise.all(posts.map(async (post) => {
                const cover = await getCoverImages(post.bookIsbn);
                return { ...post, cover };
            }));

            return { totalCount, posts: postsWithCovers };
        } catch (error) {
            console.error("독후감 목록 조회 에러:", error);
            return { posts: [], totalCount: 0 };
        }
    };

    // 독후감 커버 이미지 가져오기
    const getCoverImages = async (isbn) => {
        try {
            const response = await fetch(`/book/cover?isbn=${isbn}`);
            return await response.text();
        } catch (e) {
            return "/images/common/default-book-cover.png";
        }
    };

    return {getPostList : getPostList, getCoverImages : getCoverImages};
})();