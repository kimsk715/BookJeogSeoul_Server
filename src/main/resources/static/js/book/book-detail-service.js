const bookDetailService = (() => {

    // 책 스크랩 개수 받기
    const getScrapCount = async () => {
        // 현재 페이지 경로에서 isbn 추출
        const path = window.location.pathname;
        const isbn = path.split("/").pop(); // "9788984374423"

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

    return { getScrapCount : getScrapCount };
})();
