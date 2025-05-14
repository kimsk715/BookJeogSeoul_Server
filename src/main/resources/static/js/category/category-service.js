const categoryBookSerivce = (() => {
    const selectByKdc = async (kdc, callback) => {
        if(kdc == null) {
            alert("전달이 잘못되어 오류가 발생했습니다.")
            return;
        }

        if (typeof callback !== 'function') {
            alert("콜백 함수가 아닙니다. 올바른 함수 형식으로 전달해주세요.");
            return;
        }

        try {
            const response = fetch(`/category/book/${kdc}`);

            if (!response.ok) throw new Error('서버 응답이 실패했습니다.');

            const bookList = await response.json();
            console.log(bookList);

            if(callback)
                callback(bookList);

        }catch (error) {
            console.error('도서 조회 중 오류:', error);
            alert("도서 정보를 불러오는 중 오류가 발생했습니다.");
            callback([]); // 빈 배열을 콜백으로 전달하여 후속 처리 가능
        }
    }
    return {selectByKdc:selectByKdc}
})();
