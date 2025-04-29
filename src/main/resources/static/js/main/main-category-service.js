const seoulApiService = (() => {
    const serviceKey = '5a51544c6d6b696d3739455a645457'; // 발급받은 인증키
    const startIdx = 1; // 조회 시작 위치
    const endIdx = 20;  // 조회 끝 위치

    const url = `https://openapi.seoul.go.kr:8088/${serviceKey}/json/SeoulLibraryBookSearchInfo/${startIdx}/${endIdx}/`;

    const getSeoulBooksInfo = async (callback) => {
        try {
            const response = await fetch(url, {
                method: 'GET',
                headers: {
                    "Content-Type": "application/json;charset-utf-8"
                }
            });

            const seoulBookInfo = await response.json();
            if (seoulBookInfo && seoulBookInfo.SeoulLibraryBookSearchInfo) {
                const books = seoulBookInfo.SeoulLibraryBookSearchInfo.row; // 책 목록

                // 각 책의 전체 정보를 그대로 넘겨줌
                if (callback) {
                    callback(books); // 전체 책 정보를 callback으로 전달
                }
            }
        } catch (error) {
            console.error('데이터 요청 오류:', error);
            return null;
        }
    };

    return { getSeoulBooksInfo };
})();