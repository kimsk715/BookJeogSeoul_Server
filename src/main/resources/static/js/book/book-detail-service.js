const bookDetailService = (() => {

    // 책 상세정보 받기
    const getBookInfo = async () => {
        const isbn = "9788984374423"; // 예시용
        const path = `/book/api/detail/${isbn}`;

        try {
            const response = await fetch(path);

            if (!response.ok) {
                console.error("서버 응답 실패");
                return null;
            }

            const data = await response.json();
            console.log(data);

            if (data && data.title) {
                // 이제는 data 자체가 책 정보이므로 바로 반환
                return data;
            } else {
                console.error("책 정보가 존재하지 않음:", data);
                return null;
            }
        } catch (error) {
            console.error("fetch 에러:", error);
            return null;
        }
    };

    return { getBookInfo };
})();
