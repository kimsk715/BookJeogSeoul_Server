const myPostService = (() => {
    let offset = 0;
    let sort = "recent";
    let isLoading = false;

    // 알라딘 커버이미지
    const fetchBookCover = async (isbn) => {
        try {
            const response = await fetch(`/book/cover?isbn=${isbn}`);
            const coverUrl = await response.text();
            return coverUrl || "/images/common/default_cover.png";
        } catch (error) {
            console.error("Error fetching book cover:", error);
            return "/images/common/default_cover.png";
        }
    };

    // 내가 쓴 독후감 가져오기
    const fetchBookPosts = async () => {
        if (isLoading) return [];
        isLoading = true;

        try {
            const response = await fetch(`/post/my-book-post?sort=${sort}&offset=${offset}`);
            const data = await response.json();

            if (data.length > 0) {
                offset += data.length;
            }

            return data;
        } catch (error) {
            console.error("Error fetching book posts:", error);
            return [];
        } finally {
            isLoading = false;
        }
    };

    // 시작위치 리셋
    const resetOffset = () => {
        offset = 0;
    };

    // 분류 업데이트
    const updateSort = async (newSort) => {
        sort = newSort;
        resetOffset();

        // 새로운 정렬 기준으로 데이터 재조회
        const posts = await fetchBookPosts();
        await myPostLayout.renderPosts(posts, true); // 기존 데이터 리셋 후 새로 렌더링
    };

    // 독후감 삭제
    const deleteBookPost = async (bookPostId) => {
        try {
            const response = await fetch(`/post/delete/${bookPostId}`, {
                method: "DELETE"
            });
            return response;
        } catch (error) {
            console.error("Error deleting post:", error);
            throw error;
        }
    };

    return {
        fetchBookCover : fetchBookCover,
        fetchBookPosts : fetchBookPosts,
        updateSort : updateSort,
        resetOffset : resetOffset,
        deleteBookPost : deleteBookPost
    };
})();
