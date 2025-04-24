const searchResultReceiverService = (() => {
    // 기부글 전체 데이터(8개씩 무한스크롤)
    const getReceiverList = async (keyword, offset = 0, sortType = "new") => {
        const response = await fetch(`/search/api/result/receivers?keyword=${keyword}&offset=${offset}&sortType=${sortType}`)
        const data = await response.json();

        const totalCount = data.totalCount;
        const receivers = data.receivers;

        return { totalCount, receivers };
    }
    return { getReceiverList : getReceiverList }
})();