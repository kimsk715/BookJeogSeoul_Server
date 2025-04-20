const bookDetailPostService = (() => {

     // 이 책의 독후감 목록
    const getThisBookAllPosts = async () => {
        const isbn = window.location.pathname.split("/").pop(); // "9788984374423"

        try {
            const response = await fetch(`/post/book/post-list?isbn=${isbn}`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json;charset-utf-8"
                }
            });

            const posts = await response.json();
            return posts;

        } catch (error) {
            console.error("fetch 에러:", error);
            return null;
        }
    }

    // 이 책의 모든 독후감 개수 가져오기
    const getThisBookPostsCount = async () => {
        const isbn = window.location.pathname.split("/").pop();

        try {
            const response = await fetch(`/post/book/post-count?isbn=${isbn}`, {
                method: "GET"
            });

            const count = await response.json();
            return count;
        } catch (error) {
            console.error("fetch 에러:", error);
            return 0;
        }
    };

    return { getThisBookAllPosts : getThisBookAllPosts, getThisBookPostsCount : getThisBookPostsCount};
})();
