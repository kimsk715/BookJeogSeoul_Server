const bookDetailService = (() => {

    // 책 스크랩 개수 받기
    const getScrapCount = async () => {
        // 현재 페이지 경로에서 isbn 추출
        const isbn = window.location.pathname.split("/").pop(); // "9788984374423"

        const url = `/book/scrap-count?isbn=${isbn}`;

        try {
            console.log("Fetch 시작");
            const response = await fetch(url);

            if (!response.ok) {
                console.error("스크랩 개수 조회 실패");
                return 0;
            }

            const count = await response.json();
            return count;

        } catch (error) {
            console.error("fetch 에러:", error);
            return 0;
        }
    };

    // 스크랩 추가
    const addBookScrap = async () => {
        const isbn = window.location.pathname.split("/").pop(); // "9788984374423"

        const response = await fetch(`/book/scrap-add?isbn=${isbn}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset-utf-8"
            }
        });

        if (!response.ok) {
            console.error("스크랩 추가 실패");
        } else {
            console.log("스크랩 추가 성공");
        }
    }

    // 스크랩 삭제
    const deleteBookScrap = async () => {
        const isbn = window.location.pathname.split("/").pop(); // "9788984374423"

        const response = await fetch(`/book/scrap-delete?isbn=${isbn}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json;charset-utf-8"
            }
        });

        if (!response.ok) {
            console.error("스크랩 삭제 실패");
        } else {
            console.log("스크랩 삭제 성공");
        }
    }

    // 이 작가의 다른 책들
    const getAuthorBooks = async () => {
        const rawAuthor = document.querySelector("#author").innerText;

        // 작가명에서 (지은이)만 추출하고 공백 제거
        const extractAuthorName = (authorField) => {
            const parts = authorField.split(",");
            for (const part of parts) {
                if (part.includes("지은이")) {
                    return part.replace(/\(지은이\)/g, "").trim(); // 공백 제거 안 함
                }
            }
            return parts[0].split("(")[0].trim(); // fallback
        };

        const author = extractAuthorName(rawAuthor);

        const path = `/book/detail/author-books?author=${encodeURIComponent(author)}`;

        try {
            const response = await fetch(path);
            if (!response.ok) {
                console.error("작가 도서 fetch 실패");
                return [];
            }

            const data = await response.json();

            return data.item || [];
        } catch (error) {
            console.error("fetch 에러:", error);
            return [];
        }
    }

    // 이 책의 독후감 목록
    const getThisBookPosts = async () => {
        const isbn = window.location.pathname.split("/").pop(); // "9788984374423"

        try {
            const response = await fetch(`/post/book/book-posts?isbn=${isbn}`, {
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

    return { getScrapCount : getScrapCount , addBookScrap : addBookScrap, deleteBookScrap : deleteBookScrap, getAuthorBooks : getAuthorBooks, getThisBookPosts : getThisBookPosts, getThisBookPostsCount : getThisBookPostsCount};
})();
