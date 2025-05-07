const scrapMypageService = (() => {

    // 책 정보 가져오기
    const getSortedBooks = async (offset, sort) => {
        try {
            const response = await fetch(`/personal/sorted-books?offset=${offset}&sort=${sort}`);
            if (!response.ok) throw new Error("Failed to fetch sorted books");

            return await response.json();
        } catch (error) {
            console.error("Error fetching sorted books:", error);
            return { books: [], hasMore: false };
        }
    };

    // 스크랩 삭제
    const deleteBookScrap = async (isbn) => {

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

    return {getSortedBooks : getSortedBooks, deleteBookScrap : deleteBookScrap}
})();