const bookDetailPostService = (() => {
    let offset = 0; //
    const limit = 6;

     // 이 책의 독후감 목록(무한스크롤)
    const getThisBookAllPosts = async () => {
        const isbn = window.location.pathname.split("/").pop(); // "9788984374423"

        try {
            const response = await fetch(`/post/book/post-list?isbn=${isbn}&offset=${offset}`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json;charset-utf-8"
                }
            });

            const newPosts = await response.json();

            // 불러올 독후감이 남아있으면
            if(newPosts.length > 0) {
                offset += limit; // limit 개수만큼 offset 증가
            }

            return newPosts;

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
