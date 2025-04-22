const bookDetailService = (() => {

    // 로그인한 회원의 해당 책 스크랩 여부 조회
    const checkBookScrap = async () => {
        const isbn = window.location.pathname.split("/").pop(); // 현재 책의 ISBN

        try {
            const response = await fetch(`/book/scrap-check?isbn=${isbn}`);
            if (!response.ok) {
                console.warn("스크랩 여부 조회 실패");
                return false;
            }

            const isScrapped = await response.json();
            return isScrapped; // true 또는 false
        } catch (error) {
            console.error("스크랩 여부 조회 중 오류:", error);
            return false;
        }
    };

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
        const isbn = window.location.pathname.split("/").pop();

        try {
            const response = await fetch(`/book/scrap-add?isbn=${isbn}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json;charset-utf-8"
                }
            });

            if (response.status === 401) {
                const msg = await response.text();
                alert(msg);
                window.location.href = "/personal/login";
                return false;
            }

            if (!response.ok) {
                console.error("스크랩 추가 실패");
                return false;
            }

            return true;
        } catch (error) {
            console.error("스크랩 추가 중 에러:", error);
            return false;
        }
    };

    // 스크랩 삭제
    const deleteBookScrap = async () => {
        const isbn = window.location.pathname.split("/").pop();

        try {
            const response = await fetch(`/book/scrap-delete?isbn=${isbn}`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json;charset-utf-8"
                }
            });

            if (!response.ok) {
                console.error("스크랩 삭제 실패");
                return false;
            }

            return true;
        } catch (error) {
            console.error("스크랩 삭제 중 에러:", error);
            return false;
        }
    };

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

    // 도서의 정보를 볼 때마다 도서 줄거리 저장
    const saveBookHistory = async () => {
        const isbn = window.location.pathname.split("/").pop(); // 상세 URL에서 isbn 추출
        const summary = document.querySelector("#intro-more")?.innerText || ""; // 줄거리 가져오기

        try {
            await fetch("/history/save", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    bookIsbn: isbn,
                    bookSummary: summary
                })
            });
            console.log("히스토리 저장 완료");
        } catch (error) {
            console.error("히스토리 저장 실패:", error);
        }
    };

    return { getScrapCount : getScrapCount , addBookScrap : addBookScrap, deleteBookScrap : deleteBookScrap, getAuthorBooks : getAuthorBooks, getThisBookPosts : getThisBookPosts, getThisBookPostsCount : getThisBookPostsCount, checkBookScrap : checkBookScrap, saveBookHistory : saveBookHistory};
})();
